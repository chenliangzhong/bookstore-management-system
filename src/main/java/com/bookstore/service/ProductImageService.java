package com.bookstore.service;

import com.bookstore.bean.Product;
import com.bookstore.bean.ProductImage;

import java.util.List;

/**
 * Created by SX-503 on 2018/9/20.
 */
public interface ProductImageService {

    int insert(ProductImage productImage);

    ProductImage selectById(Long... id);

    List<ProductImage> show(Long[] product_id);

    List<ProductImage> selectByProductId(Long id);

    int updateById(ProductImage productImage);

    int deleteBatch(Long... id);

    ProductImage listByProductId(Long product_id);

    ProductImage listById(Long id);

    List<ProductImage> select();
}
