package pp.springframework.koronaZadanie.services;

import java.util.List;
import java.util.stream.Collectors;

import pp.springframework.koronaZadanie.model.Order;
import pp.springframework.koronaZadanie.pc.ProductService;
import pp.springframework.koronaZadanie.warehouse.dto.OrderItemDTO;
import pp.springframework.koronaZadanie.warehouse.service.WarehouseService;
import pp.springframework.koronaZadanie.web.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    public OrderServiceImpl(WarehouseService warehouseService, ProductService productService) {
        this.warehouseService = warehouseService;
        this.productService = productService;
    }

    private WarehouseService warehouseService;
    private ProductService productService;

    @Override
    public OrderDTO createOrder(OrderDTO order) throws Exception {
        validate(order);
        Order savedOrder = new Order();
        //TODO zaimplementować transform and save
        return saveOrder(savedOrder);
    }

    @Override
    public OrderDTO saveOrder(Order order) {
        return OrderDTO.builder().orderId(order.getId()).build();
    }

    @Override
    public void send(String orderId) {
        Order order = null;//from repository
        List<OrderItemDTO> items = order.getItems().stream()
                .map(i -> OrderItemDTO.builder()
                        .productCode(i.getProduct().getId())
                        .number(i.getNumber())
                        .build())
                .collect(Collectors.toList());
        warehouseService.getWarehouses(items);


    }

    public void validate(OrderDTO order) throws Exception {
        //TODO poprawić validacke
        boolean check = productService.isProductAvailable(order.getId(), order.getAmount());
        if (order.getAmount() <= 0 || !check) {
            throw new Exception();
        }
    }
}