package guru.springframework.msscbrewery.pc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductService {

    private ProductRepository productRepository = new DummyProductRepository();

    public boolean isProductAvailable(String productCode, Long number) {
        if (productRepository.availableProducts().containsKey(productCode)
                && productRepository.availableProducts().get(productCode) >= number) {
            return true;
        }
        else {
            return false;
        }
    }
//    public boolean isProductAvailable(String productCode, Long number) {
//        if (productRepository.availableProducts().containsKey(productCode)
//        && productRepository.availableProducts().get(productCode) >= number) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
}
