package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.pc.ProductRepository;
import guru.springframework.msscbrewery.pc.ProductService;
import guru.springframework.msscbrewery.web.model.OrderNewDTO;
import guru.springframework.msscbrewery.web.model.ProductDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private ProductService productService;

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
    public OrderNewDTO saveNewOrder(OrderNewDTO order) throws Exception{
        validate(order);
        return OrderNewDTO.builder()
                .id(order.getId())
                .build();
    }

        public void validate(OrderNewDTO order) throws Exception{
        boolean test = new ProductService().isProductAvailable(order.getId(), order.getAmount());
        if(test == false) {
            throw new Exception("aaa");
        }
    }

}