package pp.springframework.koronaZadanie.warehouse.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class OrderItemDTO {
    private String productCode;
    private Long number;
}
