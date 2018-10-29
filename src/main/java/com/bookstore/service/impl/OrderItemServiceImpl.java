package com.bookstore.service.impl;

import com.bookstore.bean.OrderItem;
import com.bookstore.dao.OrderItemDao;
import com.bookstore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by heanxing on 2018/9/25.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;
    @Override
    public int insert(OrderItem orderitem) {
        return orderItemDao.insert(orderitem);
    }



    @Override
    public List<OrderItem> selectByOrderItemId(Long id) {
        return orderItemDao.selectByOrderItemId(id);
    }

    @Override
    public List<OrderItem> selectByProductId(Long id) {
        return orderItemDao.selectByProductId(id);
    }


    @Override
    public OrderItem selectById(Long id) {
        return orderItemDao.selectById(id);
    }

    @Override
    public List<OrderItem> selectByUserId(Long user_id) {
        return orderItemDao.selectByUserId(user_id);
    }


    @Override
    public int updateById(OrderItem orderitem) {
        return orderItemDao.updateById(orderitem);
    }

    @Override
    public int deleteById(Long id) {
        return orderItemDao.deleteById(id);
    }

    @Override
    public List<OrderItem> selectByUserIdAndProductId(Long user_id) {
        return orderItemDao.selectByUserIdAndProductId(user_id);
    }

    @Override
    public OrderItem selectByUserIdOfOrder(Long user_id) {
        return orderItemDao.selectByUserIdOfOrder(user_id);
    }
}
