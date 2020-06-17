package pp.springframework.koronaZadanie.warehouse.service;

import java.util.List;

import pp.springframework.koronaZadanie.warehouse.dto.OrderItemDTO;

public interface WarehouseService {
    List<pp.springframework.koronaZadanie.web.dto.OrderItemDTO> getWarehouses(List<OrderItemDTO> items);
}
