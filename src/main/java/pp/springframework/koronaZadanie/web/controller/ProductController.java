package pp.springframework.koronaZadanie.web.controller;

import lombok.RequiredArgsConstructor;
import pp.springframework.koronaZadanie.services.OrderService;
import pp.springframework.koronaZadanie.web.dto.OrderDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/order/")
@RestController
public class ProductController {


    private final OrderService orderService;

    public ProductController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody OrderDTO orderDTO) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        orderService.createOrder(orderDTO);
        try {
            OrderDTO savedDto = orderService.createOrder(orderDTO);
            return new ResponseEntity(headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }

}
