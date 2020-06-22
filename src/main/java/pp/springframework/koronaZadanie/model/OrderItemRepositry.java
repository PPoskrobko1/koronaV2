package pp.springframework.koronaZadanie.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepositry extends JpaRepository <OrderItem, Integer> {
}
