package com.bookstore.controller;

import com.bookstore.bean.*;
import com.bookstore.common.UserManager;
import com.bookstore.enums.OrderEnum;
import com.bookstore.service.OrderService;
import com.bookstore.service.ProductService;
import com.bookstore.service.ReviewService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by ${邹} on 2018/9/25.
 */
@RestController
@RequestMapping("api/review")
public class ReviewController extends BaseApiController{
    @Autowired
    ReviewService reviewService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size) {
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<Review>(reviewService.select()));
    }
    @PostMapping("/delete")
    public Map<String,Object> delete(@RequestParam Long id){

        Review review = reviewService.selectById(id);
        Product product = productService.selectById(review.getProductId());
        Integer reviewCount = product.getReviewCount() - 1;
        product.setId(review.getProductId());
        product.setReviewCount(reviewCount);

        if (reviewService.deleteById(id) > 0){
            productService.updateById(product);
            return onSuccessRep("删除成功");
        }
        return onBadResp("删除失败");
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam String content, @RequestParam Long order_id, @RequestParam Long productId, HttpServletRequest request) {

        if (content == null || content.trim().length() == 0) return onBadResp("评价内容不能为空");

        User currentUser = UserManager.getUser(request);
        Review review = new Review();
        review.setContent(content);
        review.setCreateDate(new Date());
        review.setUserId(currentUser.getId());
        review.setProductId(productId);

        Order order = new Order();
        order.setId(order_id);
        order.setOrderEnum(OrderEnum.FINISH);

        if (reviewService.insert(review) > 0) {
            Product product = productService.selectById(productId);
            Integer reviewCount = product.getReviewCount() + 1;
            product.setId(productId);
            product.setReviewCount(reviewCount);
            productService.updateById(product);

            orderService.updateById(order);
            return onSuccessRep("添加成功");
        }

        return onBadResp("添加失败");
    }
}
