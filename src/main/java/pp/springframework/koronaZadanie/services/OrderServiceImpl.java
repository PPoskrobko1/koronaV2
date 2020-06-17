package pp.springframework.koronaZadanie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pp.springframework.koronaZadanie.model.Order;
import pp.springframework.koronaZadanie.model.OrderItem;
import pp.springframework.koronaZadanie.model.OrderRepository;
import pp.springframework.koronaZadanie.model.Product;
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

    private OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO order) throws Exception {
        validate(order);
        Order savedOrder = Order.builder()
                .items(order.getItems().stream()
                    .map(i -> OrderItem.builder()
                        .number(i.getNumber())
                        .product(Product.builder().id(i.getProductCode()).build())
                        .build()).collect(Collectors.toList()))
                .build();
        return saveOrder(orderRepository.save(savedOrder));
    }

    @Override
    public OrderDTO saveOrder(Order order) {
        return OrderDTO.builder()
                .orderId(order.getId()).build();
    }

    @Override
    public void send(String orderId) {
        Order order = null;//from repository
        List<pp.springframework.koronaZadanie.warehouse.dto.OrderDTO> items = order.getItems().stream()
                .map(i -> OrderItemDTO.builder()
                        .productCode(i.getProduct().getId())
                        .number(i.getNumber())
                        .build())
                .collect(Collectors.toList());
        warehouseService.getWarehouses(items);


    }

    public void validate(OrderDTO order) throws Exception {
        List<Boolean> check = new ArrayList<>();
        order.getItems().forEach(item -> {
            if (item.getNumber() <= 0)
                check.add(false);
            else
                check.add(productService.isProductAvailable(item.getProductCode(), item.getNumber()));
        });
        if (check.contains(false)) {
            throw new Exception();
        }
    }
}