package pp.springframework.koronaZadanie.waySvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private String orderNumber;
    private Location location;
    private List<OrderItemDTO> orderItems;

    @Data
    @Builder
    public class Location {
        private Integer x;
        private Integer y;
    }
}
