package pp.springframework.koronaZadanie.delivery.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class OrderDTO {
    private List<OrderItemDTO> items;
}
