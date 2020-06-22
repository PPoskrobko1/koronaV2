package pp.springframework.koronaZadanie.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
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
    @Column(name = "product_code")
    private String productCode;
    private Integer quantity;
    @Column(name = "warehouse_code")
    private String warehouseCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order = new Order();

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", quantity=" + quantity +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", orderId=" + order.getId() +
                '}';
    }
}
