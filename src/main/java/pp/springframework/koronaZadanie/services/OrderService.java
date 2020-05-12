package pp.springframework.koronaZadanie.services;

import pp.springframework.koronaZadanie.model.Order;
import pp.springframework.koronaZadanie.web.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO order) throws Exception;
    OrderDTO saveOrder(Order order);

    void send(String orderId);
}
