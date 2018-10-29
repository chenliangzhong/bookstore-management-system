package com.bookstore.controller;

import com.bookstore.bean.*;
import com.bookstore.common.UserManager;
import com.bookstore.service.OrderItemService;
import com.bookstore.service.ProductImageService;
import com.bookstore.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by heanxing on 2018/10/10.
 */
@RestController
@RequestMapping("/api/cart")
public class CartController extends BaseApiController {

    @Autowired
    ProductService productService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    ProductImageService productImageService;

    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "5") Integer page_size, HttpServletRequest request){

        PageHelper.startPage(page_num, page_size);

        User currentUser = UserManager.getUser(request);

//        List<Product> products = new LinkedList<>();
//
//        List<OrderItem> orderItems = orderItemService.selectByUserId(currentUser.getId());
//        for (OrderItem orderItem : orderItems) {
//            if (orderItem.getOrder_id() == null) {
//                products.add(productService.selectById(orderItem.getProduct_id()));
//            }
//        }

        return onDataResp(new MyPageInfo<OrderItem>(orderItemService.selectByUserIdAndProductId(currentUser.getId())));
    }

    @PostMapping("/insert")
    public Map<String, Object> insert(@RequestParam Long product_id, HttpServletRequest request) {

        User currentUser = UserManager.getUser(request);

        List<OrderItem> orderItems = orderItemService.selectByUserId(currentUser.getId());
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProduct_id().intValue() == product_id && orderItem.getOrder_id() == null) {
                orderItem.setNumber(orderItem.getNumber() + 1);
                orderItemService.updateById(orderItem);
                return onSuccessRep("添加成功");
            }
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(1);
        orderItem.setProduct_id(product_id);
        orderItem.setUser_id(currentUser.getId());
        if (orderItemService.insert(orderItem) > 0) return onSuccessRep("添加成功");
        return onBadResp("添加失败");
    }

    @PostMapping("delete")
    public Map<String, Object> delete(Long product_id, HttpServletRequest request) {

        User currentUser = UserManager.getUser(request);

        List<OrderItem> orderItems = orderItemService.selectByUserId(currentUser.getId());
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProduct_id().intValue() == product_id && orderItem.getOrder_id() == null) {
                orderItemService.deleteById(orderItem.getId());
                return onSuccessRep("删除成功");
            }
        }
        return onBadResp("删除失败");
    }

}
