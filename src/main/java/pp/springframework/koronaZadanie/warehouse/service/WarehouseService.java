package pp.springframework.koronaZadanie.warehouse.service;

import java.util.List;

import pp.springframework.koronaZadanie.warehouse.dto.OrderDTO;
import pp.springframework.koronaZadanie.warehouse.dto.OrderItemDTO;

public interface WarehouseService {
    List<OrderItemDTO> getWarehouses(OrderDTO order);
}
