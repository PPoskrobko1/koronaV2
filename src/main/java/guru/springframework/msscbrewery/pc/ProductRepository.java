package guru.springframework.msscbrewery.pc;

import java.util.Map;

public interface ProductRepository {
    Map<String, Integer> availableProducts();
}
