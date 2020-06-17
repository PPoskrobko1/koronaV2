package pp.springframework.koronaZadanie.delivery.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class OrderItemDTO {
    private String productCode;
    private Long number;
}
