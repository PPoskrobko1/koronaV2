package guru.springframework.msscbrewery.pc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductService {

    private ProductRepository productRepository = new DummyProductRepository();

    public boolean isProductAvailable(String productCode, Long number) {
        return productRepository.availableProducts().get(productCode) >= number;
    }

    public ResponseEntity<Object> checkProduct(String productCode) {
        if (!productRepository.availableProducts().containsKey(productCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }
}
