package com.bookstore.service;

import com.bookstore.bean.Cart;

import java.util.List;

/**
 * Created by heanxing on 2018/10/10.
 */
public interface CartService {
    List<Cart> seleByUser(Long user_id);
}
