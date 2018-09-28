package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.Order;
import com.bookstore.bean.OrderItem;
import com.bookstore.bean.User;
import com.bookstore.service.OrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by heanxing on 2018/9/25.
 */
@RestController
@RequestMapping("/api/order")
public class OrderController  extends BaseApiController{
    @Autowired
    OrderService orderService;


    @GetMapping("/select")
    public Map<String, Object> select(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size,@RequestParam Long id){
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<Order>(orderService.select(id)));
    }
    @GetMapping("/selectlist")
    public MyPageInfo<Order> selectlist(@RequestParam(required = true,defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer pageSize)
    {
        PageHelper.startPage(pageNo,pageSize);
        return new MyPageInfo<Order>(orderService.selectList());
    }
    @GetMapping("/selectByOrderId")
    public MyPageInfo<Order> selectByOrderId (@RequestParam(required = true,defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer pageSize,@RequestParam Long id){
        PageHelper.startPage(pageNo,pageSize);
        return new MyPageInfo<Order>(orderService.selectByOrderId(id));
    }
    @GetMapping("/selectById")
    public Map<String,Object> selectById(@RequestParam Long id){
       return onDataResp(orderService.selectById(id));
    }

    @GetMapping("/insert")
    public Map<String,Object> insert(@RequestParam Long user_id,@RequestParam String address,@RequestParam String receivre,@RequestParam String mobile, @RequestParam String order_code,
                                     @RequestParam String user_message, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date create_date ) {
        if (user_message != null && user_message.trim().length() == 0) return onBadResp("联系人不能为空");
        if (address != null && address.trim().length() == 0) return onBadResp("收货地址不能为空");
        if (receivre != null && receivre.trim().length() == 0) return onBadResp("收货人不能为空");
        if (mobile != null && mobile.trim().length() == 0) return onBadResp("联系电话不能为空");
        if (order_code != null && order_code.trim().length() == 0) return onBadResp("订单编号不能为空");
        if (create_date == null) return onBadResp("创建时间不能为空");
        Order order = new Order();
        OrderItem orderItem= new OrderItem();
        order.setUser_id(user_id);
        order.setOrder_code(order_code);
        order.setAddress(address);
        order.setReceivre(receivre);
        order.setMobile(mobile);
        order.setUser_message(user_message);
        order.setCreate_date(create_date);
        order.setStatus(0);
        if (orderService.insert(order) > 0) return onSuccessRep("添加成功");
        return onBadResp("添加失败");
    }
    //支付成功
    @GetMapping("/payment")
    public Map<String,Object> payment(@RequestParam Long id, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date pay_date){
        if (pay_date == null) return onBadResp("创建时间不能为空");
        Order order=new Order();
        order.setId(id);
        order.setPay_date(pay_date);
        order.setStatus(1);
        if (orderService.updateById(order)>0) return onSuccessRep("支付成功");
        return onBadResp("支付失败");
    }
    //发货
    @PostMapping("/delivery")
    public Map<String,Object> shipments(@RequestParam Long id, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date delivery_date){
        if (delivery_date == null) return onBadResp("创建时间不能为空");
        Order order=new Order();
        order.setId(id);
        order.setDelivery_date(delivery_date);
        order.setStatus(2);
        if (orderService.updateById(order)>0) return onSuccessRep("发货成功");
        return onBadResp("发货失败");
    }
    @PostMapping("/confirm")
    public Map<String,Object> confirm(@RequestParam Long id, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date confirm_date){
        if (confirm_date == null) return onBadResp("创建时间不能为空");
        Order order=new Order();
        order.setId(id);
        order.setConfirm_date(confirm_date);
        order.setStatus(3);
        if (orderService.updateById(order)>0) return onSuccessRep("收货成功");
        return onBadResp("收货失败");
    }
    @PostMapping("/cancel")
    public Map<String,Object> cancel (@RequestParam Long id){
        Order order=new Order();
        order.setId(id);
        order.setStatus(5);
        if (orderService.updateById(order)>0) return onSuccessRep("取消订单成功");
        return onBadResp("取消订单失败");
    }




}
