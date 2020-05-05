package guru.springframework.msscbrewery.pc;

import guru.springframework.msscbrewery.web.dto.ProductDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DummyProductRepository implements ProductRepository {
    public Map<String, Integer> availableProducts() {
        Map<String,Integer> products = new HashMap<String,Integer>();
        products.put("Article1", 2);
        products.put("Article2", 3);
        return products;
    }

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
}
