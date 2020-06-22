package pp.springframework.koronaZadanie.services;

import org.springframework.stereotype.Service;
import pp.springframework.koronaZadanie.web.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO order) throws Exception;
}
