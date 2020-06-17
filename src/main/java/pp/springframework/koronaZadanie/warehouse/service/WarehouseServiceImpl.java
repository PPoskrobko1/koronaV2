package pp.springframework.koronaZadanie.warehouse.service;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pp.springframework.koronaZadanie.warehouse.dto.OrderDTO;
import pp.springframework.koronaZadanie.warehouse.dto.OrderItemDTO;

@ConfigurationProperties(prefix = "wh.svc", ignoreUnknownFields = false)
@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final String WAREHOUSE_PATH_V1 = "/xxx/v1/yyy/";
    private String apihost;

    private final RestTemplate restTemplate;

    public WarehouseServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<OrderItemDTO> getWarehouses(OrderDTO order) {

        HttpEntity<pp.springframework.koronaZadanie.warehouse.dto.OrderDTO> request = new HttpEntity<>(order);
        ResponseEntity<List<OrderItemDTO>> response = restTemplate.exchange(
                apihost + WAREHOUSE_PATH_V1,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<List<OrderItemDTO>>() {}
        );
        return response.getBody();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
