package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.OrderNewDTO;
import guru.springframework.msscbrewery.web.model.ProductDto;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by jt on 2019-04-20.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public ProductDto getBeerById(UUID beerId) {
        return ProductDto.builder()
                .id(UUID.randomUUID())
                .productName("Article1")
                .amount(20)
                .category("groceries")
                .subCategory("vegetables")
                .build();
    }

    @Override
    public OrderNewDTO saveNewOrder(UUID id, Integer amount) {
        return OrderNewDTO.builder()
                .id(UUID.randomUUID())
                .build();
    }

}
