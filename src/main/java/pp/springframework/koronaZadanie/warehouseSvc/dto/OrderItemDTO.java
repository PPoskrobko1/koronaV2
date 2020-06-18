package pp.springframework.koronaZadanie.warehouseSvc.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class OrderItemDTO {
    private String productCode;
    private Integer number;
    private String warehouseId;
}
