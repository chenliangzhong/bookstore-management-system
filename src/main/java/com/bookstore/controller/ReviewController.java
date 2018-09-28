package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.Review;
import com.bookstore.service.ReviewService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size) {
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<Review>(reviewService.select()));
    }
    @GetMapping("delete")
    public Map<String,Object> delete(@RequestParam Long id){
        reviewService.deleteById( id );
        return onSuccessRep( "删除成功" );
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam String content,@RequestParam Long userId,@RequestParam Long productId) {

        if (content == null || content.trim().length() == 0) return onBadResp("content 不能为空");
        if (userId == null) return onBadResp("userId 不能为空");
        if (productId == null ) return onBadResp("productId 不能为空");

        Review review = new Review();
        review.setContent( content );
        review.setCreateDate( new Date() );
        review.setUserId( userId );
        review.setProductId( productId );

        if (reviewService.insert(review) > 0) return onSuccessRep("添加成功");
        return onBadResp("添加失败");
    }
}
