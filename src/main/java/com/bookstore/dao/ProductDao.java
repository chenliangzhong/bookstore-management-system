package com.bookstore.dao;

import com.bookstore.bean.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SX-503 on 2018/9/19.
 */
@Repository
public interface ProductDao {

    int insert(Product product);

    Product selectById(Long id);

    List <Product> selectFindProductImg(Long id);

    List<Product> select();

    Product selectByProductId(Long id);

    List<Product> findImgByCategoryId(Long category_id);

    List<Product> selectByCategoryId(Long category_id);

    int updateById(Product product);

    int deleteBatch(Long... id);

    List<Product> selectByName(@Param( "name" ) String name);
}
