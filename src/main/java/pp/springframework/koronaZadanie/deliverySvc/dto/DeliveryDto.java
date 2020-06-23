package pp.springframework.koronaZadanie.deliverySvc.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryDto {

    private List<OrderItemDto> items;
    private Integer routeLength;
}
