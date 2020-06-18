package pp.springframework.koronaZadanie.waySvc.client;

import pp.springframework.koronaZadanie.waySvc.dto.OrderDTO;

public interface WayClient {
    OrderDTO findOptimalRoute(OrderDTO order);
}
