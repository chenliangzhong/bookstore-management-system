package com.bookstore.service;

import com.bookstore.bean.Order;
import com.bookstore.bean.OrderItem;

import java.util.List;

/**
 * Created by heanxing on 2018/9/25.
 */
public interface OrderService {
    int insert(Order order);

    List<Order> list();

    List<Order> selectList();

    List<Order> select ();

    List<Order> selectByOrderId(Long id);

    Order selectById(Long id);

    int updateById(Order order);

    int updateByStatus(int status);

    int deleteById(Long id);
}
