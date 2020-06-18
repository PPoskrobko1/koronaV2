package pp.springframework.koronaZadanie.warehouseSvc.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDTO {

    private Location location;

    private List<OrderItemDTO> orderItem;

    @Data
    @Builder
    public class Location {
        private Integer x;
        private Integer y;
    }

}
