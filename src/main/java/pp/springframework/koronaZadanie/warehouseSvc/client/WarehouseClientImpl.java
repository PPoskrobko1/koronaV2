package pp.springframework.koronaZadanie.warehouseSvc.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pp.springframework.koronaZadanie.warehouseSvc.dto.OrderDTO;
import pp.springframework.koronaZadanie.warehouseSvc.dto.OrderResDTO;

@ConfigurationProperties(prefix = "wh.svc", ignoreUnknownFields = false)
@Component
public class WarehouseClientImpl implements WarehouseClient {

    private final String WAREHOUSE_PATH_V1 = "/warehouse/order";
    private String apihost;

    private final RestTemplate restTemplate;

    public WarehouseClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public OrderResDTO getWarehouses(OrderDTO order) {

        HttpEntity<OrderDTO> request = new HttpEntity<>(order);
        ResponseEntity<OrderResDTO> response = restTemplate.postForEntity(
                apihost + WAREHOUSE_PATH_V1,
                request,
                OrderResDTO.class
        );
        return response.getBody();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
