package guru.springframework.msscbrewery.pc;

import java.util.HashMap;
import java.util.Map;

public class DummyProductRepository implements ProductRepository {
    public Map<String, Integer> availableProducts() {
        Map<String,Integer> products = new HashMap<String,Integer>();
        products.put("Article1", 2);
        products.put("Article2", 3);
        return products;
    }
}
