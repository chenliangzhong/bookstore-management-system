package com.bookstore.service.impl;

import com.bookstore.bean.Cart;
import com.bookstore.dao.CartDao;
import com.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by heanxing on 2018/10/10.
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartDao;
    @Override
    public List<Cart> seleByUser(Long user_id) {
        return cartDao.seleByUser(user_id);
    }
}
