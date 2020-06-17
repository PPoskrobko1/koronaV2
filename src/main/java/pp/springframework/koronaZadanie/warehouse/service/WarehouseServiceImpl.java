package pp.springframework.koronaZadanie.warehouse.service;

import java.util.List;

import org.springframework.stereotype.Service;
import pp.springframework.koronaZadanie.web.dto.OrderItemDTO;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Override
    public List<OrderItemDTO> getWarehouses(List<pp.springframework.koronaZadanie.warehouse.dto.OrderItemDTO> items) {
        //TODO wywołać usługę zwracającą magazyny
        return null;
    }
}
