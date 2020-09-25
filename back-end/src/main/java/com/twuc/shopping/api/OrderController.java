package com.twuc.shopping.api;

import com.twuc.shopping.domain.Order;
import com.twuc.shopping.po.OrderPO;
import com.twuc.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity addOrder(@RequestBody @Valid Order order) {
        orderService.save(order);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/orders")
    public ResponseEntity getOrders() {
        List<OrderPO> orderPOs = orderService.findAll();
        return ResponseEntity.ok(orderPOs);
    }

    @DeleteMapping("/order/{id}")
    @Transactional
    public ResponseEntity deleteOrder(@PathVariable int id) {
        orderService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
