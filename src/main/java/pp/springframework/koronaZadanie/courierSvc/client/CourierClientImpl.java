package pp.springframework.koronaZadanie.courierSvc.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pp.springframework.koronaZadanie.courierSvc.dto.CourierIdDto;

import java.net.URI;

@ConfigurationProperties(prefix = "courier.svc", ignoreUnknownFields = false)
@Component
public class CourierClientImpl implements CourierClient {



    private final String COURIER_SVC_PATH_V1 = "/corona";
    private String apihost;

    private final RestTemplate restTemplate;

    public CourierClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer calculateCourier(Integer deliveryId) {

        RequestEntity request = new RequestEntity(HttpMethod.POST,
                URI.create(apihost + COURIER_SVC_PATH_V1 + "/couriers/assignDelivery/" + deliveryId.toString()));
        ResponseEntity<CourierIdDto> response = restTemplate.exchange(
                request,
                CourierIdDto.class
        );
        return response.getBody().getCourierId();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
