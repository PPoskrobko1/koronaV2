package pp.springframework.koronaZadanie.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pp.springframework.koronaZadanie.services.OrderService;
import pp.springframework.koronaZadanie.web.dto.LocationDTO;
import pp.springframework.koronaZadanie.web.dto.OrderDTO;
import pp.springframework.koronaZadanie.web.dto.OrderItemDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
@Slf4j
class ProductControllerTest {

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

    @MockBean
    OrderService orderService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void saveOrder() throws Exception{

        OrderDTO orderDTO = OrderDTO.builder()
                .location(LocationDTO.builder().x(LOCATION_X_1).y(LOCATION_Y_6).build())
                .item(OrderItemDTO.builder().productCode(PRODUCT_CODE_APPLE).quantity(QUANTITY_1).build())
                .item(OrderItemDTO.builder().productCode(PRODUCT_CODE_ORANGE).quantity(QUANTITY_10).build())
                .build();
        String jsonOrderDTO = objectMapper.writeValueAsString(orderDTO);
        log.info("Order: " + jsonOrderDTO);
        mockMvc.perform(post("/api/v1/order/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonOrderDTO))
                .andExpect(status().isCreated());


    }
}