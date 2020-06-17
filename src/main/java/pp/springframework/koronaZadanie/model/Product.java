package pp.springframework.koronaZadanie.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String id;
    private String category;
    private String name;
    private Integer amount;
    private String info;
}