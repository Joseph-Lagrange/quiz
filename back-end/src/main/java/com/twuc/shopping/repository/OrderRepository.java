package com.twuc.shopping.repository;

import com.twuc.shopping.domain.Order;
import com.twuc.shopping.po.OrderPO;
import com.twuc.shopping.po.ProductPO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderPO, Integer> {

    @Override
    List<OrderPO> findAll();
}
