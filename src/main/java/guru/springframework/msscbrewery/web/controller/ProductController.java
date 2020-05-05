package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.OrderService;
import guru.springframework.msscbrewery.web.model.ProductDto;
import guru.springframework.msscbrewery.web.model.OrderNewDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/products")
@RestController
public class ProductController {

    private final OrderService orderService;

    public ProductController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping({"/{productId}"})
    public ResponseEntity<ProductDto> getProducts(@PathVariable("productId") UUID productId){

        return new ResponseEntity<>(orderService.getBeerById(productId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestParam(value = "id") String id, @RequestParam(value = "amount") Long amount) throws Exception {
        if(amount > 0) {
            OrderNewDTO savedDto = orderService.saveNewOrder(OrderNewDTO.builder().id(id).amount(amount).build());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/api/v1/products" + savedDto.getId().toString());
            return new ResponseEntity(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
