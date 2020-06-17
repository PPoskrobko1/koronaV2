package pp.springframework.koronaZadanie.warehouse.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDTO {

    private Location location;

    private List<OrderItemDTO> orderItem;

    @Data
    public class Location {
        private Integer x;
        private Integer y;
    }

}
