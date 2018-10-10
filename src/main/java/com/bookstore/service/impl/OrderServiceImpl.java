package com.bookstore.service.impl;

import com.bookstore.bean.Order;
import com.bookstore.bean.OrderItem;
import com.bookstore.dao.OrderDao;
import com.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by heanxing on 2018/9/25.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public int insert(Order order) {
        return orderDao.insert(order);
    }

    @Override
    public List<Order> list() {
        return orderDao.list();
    }

    @Override
    public List<Order> selectList() {
        return orderDao.selectList();
    }

    @Override
    public List<Order> select() {
        return orderDao.select();
    }


    @Override
    public List<Order> selectByOrderId(Long id) {
        return orderDao.selectByOrderId(id);
    }
    
    @Override
    public Order selectById(Long id) {
        return orderDao.selectById(id);
    }

    @Override
    public int updateById(Order order) {
        return orderDao.updateById(order);
    }


    @Override
    public int updateByStatus(int status) {
        return orderDao.updateByStatus(status);
    }

    @Override
    public int deleteById(Long id) {
        return orderDao.deleteById(id);
    }
}
