package com.twuc.shopping.api;

import com.twuc.shopping.domain.Order;
import com.twuc.shopping.po.OrderPO;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.service.OrderService;
import com.twuc.shopping.service.ProductService;
import com.twuc.shopping.vo.OrderVO;
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

    @Autowired
    ProductService productService;

    @PostMapping("/order/{id}")
    public ResponseEntity addOrder(@PathVariable int id, @RequestBody @Valid Order order) {
        Optional<ProductPO> optional = productService.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        OrderPO orderPO = orderService.save(order, optional.get());
        OrderVO orderVO = OrderVO.builder().id(orderPO.getId())
                .name(orderPO.getName())
                .number(orderPO.getNumber())
                .price(orderPO.getPrice())
                .unit(orderPO.getUnit()).build();
        return ResponseEntity.ok(orderVO);
    }

    @GetMapping("/orders")
    public ResponseEntity getOrders() {
        List<OrderPO> orderPOs = orderService.findAll();
        return ResponseEntity.ok(orderPOs);
    }

    @DeleteMapping("/order/{id}")
    @Transactional
    public ResponseEntity deleteOrder(@PathVariable int id) {
        Optional<OrderPO> optional = orderService.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        orderService.deleteById(id);
        return ResponseEntity.ok(optional.get());
    }

}
