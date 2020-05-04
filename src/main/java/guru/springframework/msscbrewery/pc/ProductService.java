package guru.springframework.msscbrewery.pc;

public class ProductService {

    private ProductRepository productRepository = new DummyProductRepository();

    public boolean isProductAvailable(String productCode, Long number) {
        return productRepository.availableProducts().get(productCode) >= number;
    }
}
