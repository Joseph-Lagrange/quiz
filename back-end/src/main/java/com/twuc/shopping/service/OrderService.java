package com.twuc.shopping.service;

import com.twuc.shopping.domain.Order;
import com.twuc.shopping.po.OrderPO;
import com.twuc.shopping.repository.OrderRepository;

import java.util.List;

public class OrderService {

    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderPO> findAll() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        OrderPO orderPO = new OrderPO();
        orderPO.setName(order.getName());
        orderPO.setPrice(order.getPrice());
        orderPO.setUnit(order.getUnit());
        orderPO.setNumber(order.getNumber());
        orderRepository.save(orderPO);
    }
}
