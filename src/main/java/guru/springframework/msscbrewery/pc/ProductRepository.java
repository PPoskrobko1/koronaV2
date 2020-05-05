package guru.springframework.msscbrewery.pc;

import guru.springframework.msscbrewery.model.Order;
import guru.springframework.msscbrewery.web.dto.ProductDto;

import java.util.Map;
import java.util.UUID;

public interface ProductRepository {
    Map<String, Integer> availableProducts();
    ProductDto getBeerById(UUID beerId);
    //Order save(Order order);
}
