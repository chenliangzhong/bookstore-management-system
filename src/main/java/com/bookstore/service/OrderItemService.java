package com.bookstore.service;

import com.bookstore.bean.OrderItem;

import java.util.List;

/**
 * Created by heanxing on 2018/9/25.
 */
public interface OrderItemService {

    int insert(OrderItem orderitem);

    List<OrderItem> list();

    List<OrderItem> selectByOrderItemId(Long id);

    OrderItem selectById (Long id);

    int updateById(OrderItem orderitem);

    int deleteById(Long id);
}
