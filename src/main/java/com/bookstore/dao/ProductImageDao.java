package com.bookstore.dao;

import com.bookstore.bean.Product;
import com.bookstore.bean.ProductImage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SX-503 on 2018/9/20.
 */
@Repository
public interface ProductImageDao {

    int insert(ProductImage productImage);

    ProductImage selectById(Long... id);

    List<ProductImage> select();

    int updateById(ProductImage productImage);

    int deleteBatch(Long... id);
}
