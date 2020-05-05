package guru.springframework.msscbrewery.model;

import lombok.Data;

@Data
public class Order {
    private String id;
    private Product product;
    private long number;
}