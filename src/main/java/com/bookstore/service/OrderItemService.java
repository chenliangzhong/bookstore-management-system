package com.bookstore.service;

import com.bookstore.bean.OrderItem;

import java.util.List;

/**
 * Created by heanxing on 2018/9/25.
 */
public interface OrderItemService {

    int insert(OrderItem orderitem);


    List<OrderItem> selectByOrderItemId(Long id);

    List<OrderItem> selectByProductId(Long id);

    OrderItem selectById (Long id);

    List<OrderItem> selectByUserId(Long user_id);

    int updateById(OrderItem orderitem);

    int deleteById(Long id);

    List<OrderItem> selectByUserIdAndProductId(Long user_id);

    OrderItem selectByUserIdOfOrder(Long user_id);
}
