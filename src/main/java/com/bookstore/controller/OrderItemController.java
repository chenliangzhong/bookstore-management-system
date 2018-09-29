package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.Order;
import com.bookstore.bean.OrderItem;
import com.bookstore.bean.ProductImage;
import com.bookstore.service.OrderItemService;
import com.github.pagehelper.PageHelper;
import org.codehaus.jackson.map.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by heanxing on 2018/9/26.
 */
@RestController
@RequestMapping("api/orderitem")
public class OrderItemController extends BaseApiController {
    @Autowired
    OrderItemService orderItemService;


    @GetMapping("/selectById")
    public Map<String,Object> selectById (@RequestParam Long id){
        return onDataResp(orderItemService.selectById(id));
    }

    @GetMapping("/selectByOrderItemId")
    public MyPageInfo<OrderItem> selectByOrderItemId (@RequestParam(required = true,defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer pageSize, @RequestParam Long id){
        PageHelper.startPage(pageNo,pageSize);
        return new MyPageInfo<OrderItem>(orderItemService.selectByOrderItemId(id));
    }
    @GetMapping("/selectByProductId")
    public Map<String,Object> selectByProductId(@RequestParam Long id){
        return onDataResp(orderItemService.selectByProductId(id));
    }







}
