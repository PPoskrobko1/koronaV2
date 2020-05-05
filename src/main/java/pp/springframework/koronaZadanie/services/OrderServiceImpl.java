package pp.springframework.koronaZadanie.services;

import pp.springframework.koronaZadanie.model.Order;
import pp.springframework.koronaZadanie.pc.ProductService;
import pp.springframework.koronaZadanie.web.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderDTO createOrder(OrderDTO order) throws Exception {
        validate(order);
        Order savedOrder = new Order();
        savedOrder.setId(order.getId());
        savedOrder.setNumber(order.getAmount());
        return saveOrder(savedOrder);
    }

    @Override
    public OrderDTO saveOrder(Order order) {
        return OrderDTO.builder().id(order.getId()).build();
    }

        public void validate(OrderDTO order) throws Exception {
            boolean check = new ProductService().isProductAvailable(order.getId(), order.getAmount());
            if (order.getAmount()<= 0 || !check) {
                throw new Exception();
            }
        }
}