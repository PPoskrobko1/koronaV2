package pp.springframework.koronaZadanie.warehouseSvc.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDTO {

    private LocationDTO location;
    private List<OrderItemDTO> orderItem;
}
