package pp.springframework.koronaZadanie.web.controller;

import pp.springframework.koronaZadanie.services.OrderService;
import pp.springframework.koronaZadanie.web.dto.OrderDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/products")
@RestController
public class ProductController {

    private final OrderService orderService;

    public ProductController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @GetMapping({"/{productId}"})
//    public ResponseEntity<ProductDto> getProducts(@PathVariable("productId") UUID productId){
//
//        return new ResponseEntity<>(orderService.getBeerById(productId), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity handlePost(@RequestParam(value = "id") String id, @RequestParam(value = "amount") Long amount) {
        HttpHeaders headers = new HttpHeaders();
        try {
            OrderDTO savedDto = orderService.createOrder(OrderDTO.builder().id(id).amount(amount).build());
            headers.add("Location", "/api/v1/products" + savedDto.getId().toString());
            return new ResponseEntity(headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }
}
