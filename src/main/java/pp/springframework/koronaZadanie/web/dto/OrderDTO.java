package pp.springframework.koronaZadanie.web.dto;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private LocationDTO location;
    @Singular
    private List<OrderItemDTO> items;

}
