package pp.springframework.koronaZadanie.pc;

import org.springframework.stereotype.Service;

@Service
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
}
