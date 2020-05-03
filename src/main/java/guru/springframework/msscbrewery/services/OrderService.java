package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.OrderNewDTO;
import guru.springframework.msscbrewery.web.model.ProductDto;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
public interface OrderService {
    ProductDto getBeerById(UUID beerId);
    OrderNewDTO saveNewOrder(OrderNewDTO order);
}
