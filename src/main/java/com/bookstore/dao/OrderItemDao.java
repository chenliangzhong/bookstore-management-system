package com.bookstore.dao;

import com.bookstore.bean.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by heanxing on 2018/9/25.
 */
@Repository
public interface OrderItemDao {

    int insert(OrderItem orderitem);

    List<OrderItem> selectByOrderItemId(Long id);

    OrderItem selectById (Long id);

    List<OrderItem> selectByProductId(Long id);

    List<OrderItem> selectByUserId(Long user_id);

    int updateById(OrderItem orderitem);

    int deleteById(Long id);

    List<OrderItem> selectByUserIdAndProductId(@Param("user_id") Long user_id);

    OrderItem selectByUserIdOfOrder(@Param("user_id") Long user_id);
}
