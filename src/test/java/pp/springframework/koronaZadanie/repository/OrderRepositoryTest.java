package pp.springframework.koronaZadanie.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pp.springframework.koronaZadanie.OrderApplication;
import pp.springframework.koronaZadanie.model.Order;
import pp.springframework.koronaZadanie.model.OrderItem;
import pp.springframework.koronaZadanie.model.OrderItemRepositry;
import pp.springframework.koronaZadanie.model.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = OrderApplication.class)
@Slf4j
@Transactional
class OrderRepositoryTest {

    private final Integer LOCATION_X_1 = 1;
    private final Integer LOCATION_Y_1 = 1;
    private final Integer LOCATION_X_7 = 7;
    private final Integer LOCATION_Y_6 = 6;


    private final String WH_CODE_1 = "WH_1";
    private final Integer QUANTITY_1 = 1;
    private final String PRODUCT_CODE_APPLE = "APPLE";

    private final String WH_CODE_2 = "WH_2";
    private final Integer QUANTITY_10 = 10;
    private final String PRODUCT_CODE_ORANGE = "ORANGE";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepositry orderItemRepositry;

    @Test
    public void testSaveOrder() {

        Order order = Order.builder()
                .pointX(LOCATION_X_1)
                .pointY(LOCATION_Y_6).build();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(OrderItem.builder()
                .warehouseCode(WH_CODE_1)
                .quantity(QUANTITY_1)
                .productCode(PRODUCT_CODE_APPLE)
                .order(order).build());
        orderItems.add(OrderItem.builder()
                .warehouseCode(WH_CODE_2)
                .quantity(QUANTITY_10)
                .productCode(PRODUCT_CODE_ORANGE)
                .order(order).build());
        order.setItems(orderItems);

        orderRepository.save(order);
        assertThat(order.getId()).isEqualTo(1);
        assertThat(order.getPointX()).isEqualTo(LOCATION_X_1);
        assertThat(order.getPointY()).isEqualTo(LOCATION_Y_6);
        assertThat(order.getItems().get(0).getId()).isEqualTo(1);
        assertThat(order.getItems().get(0).getWarehouseCode()).isEqualTo(WH_CODE_1);
        assertThat(order.getItems().get(0).getQuantity()).isEqualTo(QUANTITY_1);
        assertThat(order.getItems().get(0).getProductCode()).isEqualTo(PRODUCT_CODE_APPLE);
        assertThat(order.getItems().get(0).getOrder().getId()).isEqualTo(1);
        assertThat(order.getItems().get(1).getId()).isEqualTo(2);
        assertThat(order.getItems().get(1).getWarehouseCode()).isEqualTo(WH_CODE_2);
        assertThat(order.getItems().get(1).getQuantity()).isEqualTo(QUANTITY_10);
        assertThat(order.getItems().get(1).getProductCode()).isEqualTo(PRODUCT_CODE_ORANGE);
        assertThat(order.getItems().get(1).getOrder().getId()).isEqualTo(1);

    }

}