package pp.springframework.koronaZadanie.warehouseSvc.client;

import pp.springframework.koronaZadanie.warehouseSvc.dto.OrderDTO;
import pp.springframework.koronaZadanie.warehouseSvc.dto.OrderResDTO;

public interface WarehouseClient {
    OrderResDTO getWarehouses(OrderDTO order);
}
