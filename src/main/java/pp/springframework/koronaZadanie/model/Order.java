package pp.springframework.koronaZadanie.model;

import lombok.Data;

@Data
public class Order {
    private String id;
    private Product product;
    private long number;
}