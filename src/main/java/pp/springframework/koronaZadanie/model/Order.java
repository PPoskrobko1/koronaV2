package pp.springframework.koronaZadanie.model;

import java.util.List;

import lombok.Data;

@Data
public class Order {
    private String id;
    private List<OrderItem> items;
}