package com.example.smth.Controller;

import com.example.smth.entities.ApiResponse;
import com.example.smth.entities.OrderEntity;
import com.example.smth.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class Controller {

    @Autowired
    private final OrderService orderService;
    public Controller(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable("id") Long id) {
        OrderEntity order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @GetMapping
    public List<OrderEntity> findAllOrders() {
            return orderService.getAllOrders();
    }
    @PostMapping
    public OrderEntity createOrder( @RequestBody OrderEntity order) {
        return orderService.create(order);

    }
    @DeleteMapping("/{id}")
    public OrderEntity deleteOrder(@PathVariable Long id) {
         orderService.deleteOrderById(id);
         return null;
    }


}




