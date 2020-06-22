package pp.springframework.koronaZadanie.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;



@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
@EqualsAndHashCode(exclude = "items")
public class Order {
    @Id
    @GeneratedValue(generator = "inc_orders")
    @GenericGenerator(name = "inc_orders", strategy = "increment")
    private Integer id;

    @Column(name = "point_x")
    private Integer pointX;
    @Column(name = "point_y")
    private Integer pointY;

    @Singular
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private List<OrderItem> items;

}