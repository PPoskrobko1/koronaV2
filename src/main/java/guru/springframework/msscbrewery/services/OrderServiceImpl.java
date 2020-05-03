package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.OrderNewDTO;
import guru.springframework.msscbrewery.web.model.ProductDto;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by jt on 2019-04-20.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public ProductDto getBeerById(UUID beerId) {
        return ProductDto.builder()
                .id(UUID.randomUUID())
                .productName("Article1")
                .amount(20)
                .category("groceries")
                .subCategory("vegetables")
                .build();
    }

    @Override
    public OrderNewDTO saveNewOrder(OrderNewDTO order) {
        return OrderNewDTO.builder()
                .id(UUID.randomUUID())
                .build();
    }

//    public Order createOrder(OrderDTO order) {
//        validate(order);
//        Order createdOrder = new Order();
//        createdOrder.setItems(order.getItems()
//                .stream()
//                .map(item -> OrderItem.builder()
//                        .number(item.getNumber()).build())
//                .collect(Collectors.toList()));
//        return orderRepository.save(createdOrder);
//    }
//
//    private void validate(OrderDTO order) {
//        if(order.getItems()
//                .stream()
//                .filter(item -> !productService
//                        .isProductAvailable(item.getProductCode(), item.getNumber()))
//                .findAny().isPresent()) {
//            throw new RuntimeException("Brak takiego produktu");
//        }
//    }

}
