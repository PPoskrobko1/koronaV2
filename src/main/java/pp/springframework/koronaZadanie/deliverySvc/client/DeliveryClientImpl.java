package pp.springframework.koronaZadanie.deliverySvc.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pp.springframework.koronaZadanie.deliverySvc.dto.DeliveryDto;

import java.net.URI;

@ConfigurationProperties(prefix = "delivery.svc", ignoreUnknownFields = false)
@Component
public class DeliveryClientImpl implements DeliveryClient {



    private final String DELIVERY_SVC_PATH_V1 = "/korona/v2/delivery";
    private String apihost;

    private final RestTemplate restTemplate;

    public DeliveryClientImpl( RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer saveDelivery(DeliveryDto deliveryDto) {

        HttpEntity<DeliveryDto> request = new HttpEntity<>(deliveryDto);
        URI location = restTemplate.postForLocation(
                apihost + DELIVERY_SVC_PATH_V1,
                request
        );
        String path = location.getPath();
        return Integer.valueOf(path.substring(path.lastIndexOf("/")+1));
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
