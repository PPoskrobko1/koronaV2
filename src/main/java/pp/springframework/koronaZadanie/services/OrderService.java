package pp.springframework.koronaZadanie.services;

import pp.springframework.koronaZadanie.web.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO order) throws Exception;
}
