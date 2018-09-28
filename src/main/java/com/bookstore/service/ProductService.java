package com.bookstore.service;

import com.bookstore.bean.Product;

import java.util.List;

/**
 * Created by SX-503 on 2018/9/19.
 */
public interface ProductService {
    int insert(Product product);

    Product selectById(Long id);

    List<Product> selectByCategoryId(Long id);

    int updateById(Product product);

    int deleteBatch(Long... id);

    List<Product> select();
}
