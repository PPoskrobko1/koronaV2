package pp.springframework.koronaZadanie.utils;

import org.springframework.stereotype.Component;
import pp.springframework.koronaZadanie.model.Order;
import pp.springframework.koronaZadanie.model.OrderItem;
import pp.springframework.koronaZadanie.warehouseSvc.dto.LocationDTO;
import pp.springframework.koronaZadanie.warehouseSvc.dto.OrderItemDTO;
import pp.springframework.koronaZadanie.waySvc.dto.WarehouseDTO;
import pp.springframework.koronaZadanie.web.dto.OrderDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConverterImpl implements Converter {

    @Override
    public Order convertWebOrderDtoToEntity(OrderDTO order) {
        Order orderEntity = Order.builder()
                .pointX(order.getLocation().getX())
                .pointY(order.getLocation().getY()).build();
        List<OrderItem> items = order.getItems().stream().map(i -> OrderItem.builder()
                .quantity(i.getQuantity())
                .productCode(i.getProductCode())
                .order(orderEntity)
                .build()).collect(Collectors.toList());
        orderEntity.setItems(items);
        return orderEntity;
    }

    @Override
    public pp.springframework.koronaZadanie.warehouseSvc.dto.OrderDTO convertEntityToWhSvcOrderDto(Order order) {
        return pp.springframework.koronaZadanie.warehouseSvc.dto.OrderDTO.builder()
                .location(pp.springframework.koronaZadanie.warehouseSvc.dto.LocationDTO.builder()
                        .x(order.getPointX())
                        .y(order.getPointY()).build())
                .orderItem(order.getItems().stream().map(i -> OrderItemDTO.builder()
                        .productCode(i.getProductCode())
                        .number(i.getQuantity())
                        .build()).collect(Collectors.toList())).build();
    }

    @Override
    public pp.springframework.koronaZadanie.waySvc.dto.OrderDTO convertEntityToWaySvcOrderDto(Order order) {
        return pp.springframework.koronaZadanie.waySvc.dto.OrderDTO.builder()
                .location(pp.springframework.koronaZadanie.waySvc.dto.LocationDTO.builder()
                        .x(order.getPointX())
                        .y(order.getPointY()).build())
                .orderItems(order.getItems().stream().map(i -> pp.springframework.koronaZadanie.waySvc.dto.OrderItemDTO.builder()
                        .orderNumber(i.getId().toString())
                        .warehouse(WarehouseDTO.builder()
                                .code(i.getWarehouseCode()).build())
                        .build()).collect(Collectors.toList())).build();
    }


}
