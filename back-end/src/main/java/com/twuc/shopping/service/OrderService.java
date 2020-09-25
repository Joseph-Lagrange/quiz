package com.twuc.shopping.service;

import com.twuc.shopping.domain.Order;
import com.twuc.shopping.po.OrderPO;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class OrderService {

    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderPO> findAll() {
        return orderRepository.findAll();
    }

    public OrderPO save(Order order, ProductPO productPO) {
        Optional<OrderPO> optional = orderRepository.findByName(order.getName());
        OrderPO orderPO;
        if (optional.isPresent()) {
            orderPO = optional.get();
            orderPO.setNumber(orderPO.getNumber() + 1);
        } else {
            orderPO = OrderPO.builder().name(order.getName())
                    .price(order.getPrice())
                    .unit(order.getUnit())
                    .number(order.getNumber())
                    .productPO(productPO).build();
        }
        orderRepository.save(orderPO);
        return orderPO;
    }

    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

    public Optional<OrderPO> findById(int id) {
        return orderRepository.findById(id);
    }
}
