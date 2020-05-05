package pp.springframework.koronaZadanie.pc;

import pp.springframework.koronaZadanie.web.dto.ProductDto;

import java.util.Map;
import java.util.UUID;

public interface ProductRepository {
    Map<String, Integer> availableProducts();
    ProductDto getBeerById(UUID beerId);
}
