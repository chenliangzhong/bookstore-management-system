package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.OrderItem;
import com.bookstore.bean.Product;
import com.bookstore.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by SX-503 on 2018/9/19.
 */
@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseApiController{

    @Autowired
    ProductService productService;

    // 增
    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam String name, @RequestParam String subtitle, @RequestParam BigDecimal original_price,
                                   @RequestParam BigDecimal promote_price, @RequestParam Integer stock, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd")Date create_date, @RequestParam Long category_id) {

        if (name == null || name.trim().length() == 0) return onBadResp("name 不能为空");
        if (subtitle == null || subtitle.trim().length() == 0) return onBadResp("subtitle 不能为空");
        if (original_price == null) return onBadResp("original_price 不能为空");
        if (promote_price == null) return onBadResp("promote_price 不能为空");
        if (stock == null) return onBadResp("stock 不能为空");
        if (create_date == null) return onBadResp("create_date 不能为空");
        if (category_id == null) return onBadResp("category_id 不能为空");

        Product product = new Product();
        product.setName(name.trim());
        product.setSubtitle(subtitle.trim());
        product.setOriginalPrice(original_price);
        product.setPromotePrice(promote_price);
        product.setStock(stock);
        product.setCreateDate(create_date);
        product.setCategoryId(category_id);

        if (productService.insert(product) > 0) return onSuccessRep("添加成功");
        return onBadResp("添加失败");
    }

    // 批量删
    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long[] id ){
        productService.deleteBatch(id);
        return onSuccessRep("删除成功");
    }

    // 改
    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long id, String name, String subtitle, BigDecimal original_price,
                                      BigDecimal promote_price, Integer stock, @DateTimeFormat(pattern="yyyy-MM-dd")Date create_date, Long category_id)
    {
        if (name != null && name.trim().length() == 0) return onBadResp("");
        if (subtitle != null && subtitle.trim().length() == 0) return onBadResp("");

        Product product = new Product();
        product.setId(id);
        if (name != null) product.setName(name.trim());
        if (subtitle != null) product.setSubtitle(subtitle.trim());
        if (original_price != null) product.setOriginalPrice(original_price);
        if (promote_price != null) product.setPromotePrice(promote_price);
        if (stock != null) product.setStock(stock);
        if (create_date != null) product.setCreateDate(create_date);
        if (category_id != null) product.setCategoryId(category_id);

        if (productService.updateById(product) > 0){onSuccessRep("修改成功");}
        return onSuccessRep("修改失败");
    }

    // 查
    @GetMapping("/list")
    public MyPageInfo<Product> select(@RequestParam(required = true,defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer pageSize)
    {
        PageHelper.startPage(pageNo,pageSize);
        return new MyPageInfo<Product>(productService.select());
    }


    @GetMapping("/selectByProductId")
    public MyPageInfo<Product> selectByProductId (@RequestParam(required = true,defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer pageSize,@RequestParam Long id){
        PageHelper.startPage(pageNo,pageSize);
        return  new MyPageInfo<Product>((List<Product>) productService.selectByProductId(id));

    }

}
