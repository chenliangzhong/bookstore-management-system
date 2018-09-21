package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.ProductImage;
import com.bookstore.service.ProductImageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by SX-503 on 2018/9/20.
 */
@RestController
@RequestMapping("/api/productImage")
public class ProductImageController extends BaseApiController{

    @Autowired ProductImageService productImageService;

    // 增
    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam String picture, @RequestParam Long product_id) {

        if (picture == null || picture.trim().length() == 0) return onBadResp("picture 不能为空");
        if (product_id == null) return onBadResp("subtitle 不能为空");

        ProductImage productImage = new ProductImage();
        productImage.setPicture(picture.trim());
        productImage.setProductId(product_id);

        if (productImageService.insert(productImage) > 0) return onSuccessRep("添加成功");
        return onBadResp("添加失败");
    }

    // 批量删
    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long[] id ){
        productImageService.deleteBatch(id);
        return onSuccessRep("删除成功");
    }

    // 改
    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long id, String picture, Long product_id )
    {
        if (picture != null && picture.trim().length() == 0) return onBadResp("");

        ProductImage productImage = new ProductImage();
        productImage.setId(id);
        if (picture != null) productImage.setPicture(picture.trim());
        if (product_id != null) productImage.setProductId(product_id);

        if (productImageService.updateById(productImage) > 0){onSuccessRep("修改成功");}
        return onSuccessRep("修改失败");
    }

    // 查
    @GetMapping("/list")
    public MyPageInfo<ProductImage> select(@RequestParam(required = true,defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer pageSize)
    {
        PageHelper.startPage(pageNo,pageSize);
        return new MyPageInfo<ProductImage>(productImageService.select());
    }
}
