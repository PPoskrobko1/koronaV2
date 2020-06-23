package pp.springframework.koronaZadanie.services;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import pp.springframework.koronaZadanie.courierSvc.client.CourierClient;
import pp.springframework.koronaZadanie.deliverySvc.client.DeliveryClient;
import pp.springframework.koronaZadanie.deliverySvc.dto.DeliveryDto;
import pp.springframework.koronaZadanie.model.Order;
import pp.springframework.koronaZadanie.model.OrderRepository;
import pp.springframework.koronaZadanie.utils.Converter;
import pp.springframework.koronaZadanie.warehouseSvc.dto.OrderItemDTO;
import pp.springframework.koronaZadanie.warehouseSvc.dto.OrderResDTO;
import pp.springframework.koronaZadanie.warehouseSvc.client.WarehouseClient;
import pp.springframework.koronaZadanie.waySvc.client.WayClient;
import pp.springframework.koronaZadanie.web.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private Converter converter;

    private WarehouseClient warehouseClient;
    private DeliveryClient deliveryClient;
    private WayClient wayClient;
    private CourierClient courierClient;

    public OrderServiceImpl(OrderRepository orderRepository,
                            Converter converter,
                            WarehouseClient warehouseClient,
                            DeliveryClient deliveryClient,
                            WayClient wayClient,
                            CourierClient courierClient) {
        this.orderRepository = orderRepository;
        this.converter = converter;
        this.warehouseClient = warehouseClient;
        this.deliveryClient = deliveryClient;
        this.wayClient = wayClient;
        this.courierClient = courierClient;
    }

    @Override
    public void createOrder(OrderDTO order) throws Exception {
        //todo converter per model/dto
        Order orderEntity = converter.convertWebOrderDtoToEntity(order);
        orderRepository.save(orderEntity);

//        Ask for nearest warehouses with required products quantity
        orderEntity = getWarehousesAndUpdate(orderEntity);

//        Calculate optimal route
        orderEntity = calculateRouteAndSaveDelivery(orderEntity);

//        Add courier to order
        calculateCourierAndSave(orderEntity);
    }

    private Order getWarehousesAndUpdate(Order order) {
        OrderResDTO whOrderRes = warehouseClient.getWarehouses(converter.convertEntityToWhSvcOrderDto(order));
        Map<Integer, OrderItemDTO> itemDTOMap = whOrderRes.getOrderItem().stream()
                .collect(Collectors.toMap(OrderItemDTO::getId, Function.identity()));
        order.getItems().forEach(item -> {
            item.setWarehouseCode(Optional.ofNullable(itemDTOMap.get(item.getId())).orElseThrow(() -> {
                log.error("Not enough produts in warehouses");
                throw new RuntimeException();
            }).getWarehouseId());
        });
        return orderRepository.save(order);
    }

    private Order calculateRouteAndSaveDelivery(Order order) {
        pp.springframework.koronaZadanie.waySvc.dto.OrderDTO wayOrderDTO = wayClient.calculateOptimalRoute(converter.convertEntityToWaySvcOrderDto(order));
        Map<String, Integer> map = wayOrderDTO.getOrderItems().stream().collect(Collectors.toMap(pp.springframework.koronaZadanie.waySvc.dto.OrderItemDTO::getOrderNumber,
                pp.springframework.koronaZadanie.waySvc.dto.OrderItemDTO::getWaypointNo));
        DeliveryDto deliveryDto = DeliveryDto.builder()
                .routeLength(wayOrderDTO.getRouteLength())
                .items(order.getItems().stream()
                        .map(i -> pp.springframework.koronaZadanie.deliverySvc.dto.OrderItemDto.builder()
                                .productId(i.getProductCode())
                                .quantity(i.getQuantity())
                                .warehouseId(i.getWarehouseCode())
                                .waypointNo(map.get(i.getId()))
                                .build()).collect(Collectors.toList())).build();
        order.setDeliveryId(deliveryClient.saveDelivery(deliveryDto));
        return orderRepository.save(order);
    }

    private Order calculateCourierAndSave(Order order) {
        order.setCourierId(courierClient.calculateCourier(order.getDeliveryId()));
        return orderRepository.save(order);
    }

}