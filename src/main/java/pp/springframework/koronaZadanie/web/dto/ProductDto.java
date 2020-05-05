package pp.springframework.koronaZadanie.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private UUID id;
    private String productName;
    private String category;
    private String subCategory;
    private Integer amount;
    private String info;
}
