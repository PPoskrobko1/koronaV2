package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.OrderNewDTO;
import guru.springframework.msscbrewery.web.model.ProductDto;

import java.util.UUID;

public interface OrderService {
    ProductDto getBeerById(UUID beerId);
    OrderNewDTO saveNewOrder(OrderNewDTO order) throws Exception;

}
