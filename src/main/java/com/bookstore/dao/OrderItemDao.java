package com.bookstore.dao;

import com.bookstore.bean.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by heanxing on 2018/9/25.
 */
@Repository
public interface OrderItemDao {

    int insert(OrderItem orderitem);

    List<OrderItem> list();

    List<OrderItem> selectByOrderItemId(Long id);

    OrderItem selectById (Long id);

    int updateById(OrderItem orderitem);

    int deleteById(Long id);
}
