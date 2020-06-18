package pp.springframework.koronaZadanie.waySvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {

    private String orderNumber;
    private WarehouseDTO warehouse;
    private Integer waypointNo;
}
