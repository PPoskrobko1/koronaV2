package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.model.Order;
import guru.springframework.msscbrewery.pc.ProductService;
import guru.springframework.msscbrewery.web.dto.OrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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