package pp.springframework.koronaZadanie.deliverySvc.client;

import pp.springframework.koronaZadanie.deliverySvc.dto.DeliveryDto;

public interface DeliveryClient {
    Integer saveDelivery(DeliveryDto deliveryDto);
}
