package com.bookstore.controller;

import com.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by heanxing on 2018/10/10.
 */
@RestController
@RequestMapping("/api/cart")
public class CartController extends BaseApiController {
    @Autowired
    CartService cartService;


    @GetMapping("/selectByUser")
    public Map<String,Object>selectByUser(@RequestParam Long user_id){
        return onDataResp(cartService.seleByUser(user_id));
    }
}
