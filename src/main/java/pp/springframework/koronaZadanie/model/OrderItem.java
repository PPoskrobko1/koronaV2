package pp.springframework.koronaZadanie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEMS")
public class OrderItem {

    @Id
    @GeneratedValue(generator = "inc_item")
    @GenericGenerator(name = "inc_item", strategy = "increment")
    private Integer id;

    @NotNull
    private String productCode;
    private Integer quantity;
    private String warehouseCode;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

}