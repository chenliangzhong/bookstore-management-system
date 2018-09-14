package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.Product;
import com.bookstore.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by SX-503 on 2018/9/11.
 */
@RestController
@RequestMapping("/api/product/")
public class ProductController extends BaseApiController {

    @Autowired
    ProductService productService;

    // 增
    @PostMapping("add")
    public Map<String, Object> add(@RequestParam String book_name, @RequestParam String author, @RequestParam String press, @RequestParam BigDecimal price, @RequestParam String image,
                                   @RequestParam String describe, @RequestParam Long stock,
                                   @RequestParam Long status, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd")Date create_time ,@RequestParam Long category_id) {

        if (book_name == null || book_name.trim().length() == 0) return onBadResp("book_name 不能为空");
        if (author == null || author.trim().length() == 0) return onBadResp("author 不能为空");
        if (press == null || press.trim().length() ==0) return onBadResp("press 不能为空");
        if (price == null ) return onBadResp("price 不能为空");
        if (image == null || image.trim().length() ==0) return onBadResp("image 不能为空");
        if (describe == null || describe.trim().length() == 0) return onBadResp("describe 不能为空");
        if (stock == null) return onBadResp("inventory 不能为空");
        if (status == null) return onBadResp("status 不能为空");
        if (create_time == null) return onBadResp("create_time 不能为空");
        if (category_id == null) return  onBadResp("category_id 不能为空");

        Product product = new Product();
        product.setBookName(book_name.trim());
        product.setAuthor(author.trim());
        product.setPress(press.trim());
        product.setPrice(price);
        product.setImage(image.trim());
        product.setDescribe(describe.trim());
        product.setStock(stock);
        product.setStatus(status);
        product.setCreateTime(create_time);
        product.setCategoryId(category_id);

        if (productService.insert(product) > 0) return onSuccessRep("添加成功");
        return onBadResp("添加失败");
    }


    // 批量删
    @PostMapping("delete")
    public Map<String, Object> delete(@RequestParam Long[] id ){
        productService.deleteBatch(id);
        return onSuccessRep("删除成功");
    }

   // 改
   @PostMapping("update")
   public Map<String, Object> update(@RequestParam Long id, String book_name, String author , String press, BigDecimal price, String image,
                                                    String describe, Long stock, Long status, @DateTimeFormat(pattern="yyyy-MM-dd") Date create_time , Long category_id)
   {
       if (book_name != null && book_name.trim().length() == 0) return onBadResp("");
       if (author != null && author.trim().length() == 0) return onBadResp("");
       if (press != null && press.trim().length() == 0) return onBadResp("");
       if (image != null && image.trim().length() == 0) return onBadResp("");
       if (describe != null && describe.trim().length() == 0) return onBadResp("");

       Product product = new Product();
       product.setId(id);
       if (book_name != null) product.setBookName(book_name.trim());
       if (author != null) product.setAuthor(author.trim());
       if (press != null) product.setPress(press.trim());
       if (price != null) product.setPrice(price);
       if (image != null) product.setImage(image.trim());
       if (describe != null) product.setDescribe(describe.trim());
       if (stock != null) product.setStock(stock);
       if (status != null) product.setStatus(status);
       if (create_time != null) product.setCreateTime(create_time);
       if (category_id != null) product.setCategoryId(category_id);

       productService.updateById(product);
       return onSuccessRep("修改成功");
   }

    // 查
    @GetMapping("select")
    public MyPageInfo<Product> select(@RequestParam(required = true,defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer pageSize)
    {
        PageHelper.startPage(pageNo,pageSize);
        return new MyPageInfo<Product>(productService.select());
    }

    // id 查
    @GetMapping("show/{id}")
    public Map<String, Object> show(@PathVariable Long id) {
        return onDataResp(productService.selectById(id));
    }
}
