package guru.springframework.msscbrewery.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private String id;
    private Product product;
    private long number;
}