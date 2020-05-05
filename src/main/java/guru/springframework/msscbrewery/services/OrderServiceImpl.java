package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.model.Order;
import guru.springframework.msscbrewery.pc.ProductService;
import guru.springframework.msscbrewery.web.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderDTO createOrder(OrderDTO order) {
        Order savedOrder = new Order();
        savedOrder.setId(order.getId());
        savedOrder.setNumber(order.getAmount());
        return saveOrder(savedOrder);
    }

    @Override
    public OrderDTO saveOrder(Order order) {
        return OrderDTO.builder().id(order.getId()).build();
    }

        public void validate(OrderDTO order) throws Exception{
        boolean test = new ProductService().isProductAvailable(order.getId(), order.getAmount());

        if(test == false) {
            throw new Exception("aaa");
        }
    }
}