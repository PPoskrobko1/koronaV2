package pp.springframework.koronaZadanie.utils;

import org.springframework.stereotype.Component;
import pp.springframework.koronaZadanie.model.Order;
import pp.springframework.koronaZadanie.web.dto.OrderDTO;

public interface Converter {
    Order convertWebOrderDtoToEntity(OrderDTO order);
    pp.springframework.koronaZadanie.warehouseSvc.dto.OrderDTO convertEntityToWhSvcOrderDto(Order order);
    pp.springframework.koronaZadanie.waySvc.dto.OrderDTO convertEntityToWaySvcOrderDto(Order order);
}
