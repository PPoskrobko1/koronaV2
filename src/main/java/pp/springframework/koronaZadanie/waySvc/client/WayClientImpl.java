package pp.springframework.koronaZadanie.waySvc.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pp.springframework.koronaZadanie.waySvc.dto.OrderDTO;

@ConfigurationProperties(prefix = "way.svc", ignoreUnknownFields = false)
@Component
public class WayClientImpl implements WayClient {

    private final String WAY_SVC_PATH_V1 = "/store/v1/delivery";
    private String apihost;

    private final RestTemplate restTemplate;

    public WayClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public OrderDTO findOptimalRoute(OrderDTO order) {

        HttpEntity<OrderDTO> request = new HttpEntity<>(order);
        ResponseEntity<OrderDTO> response = restTemplate.postForEntity(
                apihost + WAY_SVC_PATH_V1 + "/calculate",
                request,
                OrderDTO.class
        );
        return response.getBody();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
