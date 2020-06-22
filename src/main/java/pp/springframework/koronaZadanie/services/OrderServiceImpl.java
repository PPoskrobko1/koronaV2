package pp.springframework.koronaZadanie.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pp.springframework.koronaZadanie.model.Order;
import pp.springframework.koronaZadanie.model.OrderItem;
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

    public OrderServiceImpl(WarehouseClient warehouseClient,
                            Converter converter,
                            WayClient wayClient, OrderRepository orderRepository) {
        this.warehouseClient = warehouseClient;
        this.converter = converter;
        this.wayClient = wayClient;
        this.orderRepository = orderRepository;
    }

    private Converter converter;
    private WarehouseClient warehouseClient;
    private WayClient wayClient;
    private OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO order) throws Exception {
        //todo converter per model/dto
        Order orderEntity = converter.convertWebOrderDtoToEntity(order);
        orderRepository.save(orderEntity);

//        Ask for nearest warehouses with required products quantity
        OrderResDTO whOrderRes = warehouseClient.getWarehouses(converter.convertEntityToWhSvcOrderDto(orderEntity));
        //todo walidacje gdy warehouse jest pusty
        log.info("Warehouse orderResponse: " + whOrderRes.toString());
        //todo zamnkąć w jednej funckji
        Map<Integer, OrderItemDTO> orderItemDTOMap = whOrderRes.getOrderItem()
                .stream()
                .collect(Collectors.toMap(i -> i.getId(), Function.identity()));
        orderEntity.getItems().forEach(i -> {
            i.setWarehouseCode(Optional.ofNullable(orderItemDTOMap.get(i.getId())).orElseThrow().getWarehouseId());
        });

//        Calculate optimal route
        //todo wraper na metody w "modelu"
        wayClient.findOptimalRoute(converter.convertEntityToWaySvcOrderDto(orderEntity));
        //todo zapisac zmiany w bazie

        return null;
    }

}