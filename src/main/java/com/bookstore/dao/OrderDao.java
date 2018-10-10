package com.bookstore.dao;

import com.bookstore.bean.Order;
import com.bookstore.bean.OrderItem;
import com.bookstore.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by heanxing on 2018/9/25.
 */
@Repository
public interface OrderDao {

    int insert(Order order);

    List<Order> selectList();

    List<Order> list();

    List<Order> select ();

    List<Order> selectByOrderId(Long id);

    Order selectById(Long id);

    int updateById(Order order);

    int updateByStatus(int status);

    int deleteById(Long id);
}
