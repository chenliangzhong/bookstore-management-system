package com.bookstore.dao;

import com.bookstore.bean.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SX-503 on 2018/9/19.
 */
@Repository
public interface ProductDao {

    int insert(Product product);

    Product selectById(Long id);

    List<Product> selectByCategoryId(Long id);

    int updateById(Product product);

    int deleteBatch(Long... id);
}
