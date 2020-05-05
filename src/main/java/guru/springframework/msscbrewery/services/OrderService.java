package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.model.Order;
import guru.springframework.msscbrewery.web.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO order) throws Exception;
    OrderDTO saveOrder(Order order);
}
