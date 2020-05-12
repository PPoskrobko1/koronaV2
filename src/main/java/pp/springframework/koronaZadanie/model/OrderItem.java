package pp.springframework.koronaZadanie.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {

    private String id;
    private Product product;
    private long number;
}