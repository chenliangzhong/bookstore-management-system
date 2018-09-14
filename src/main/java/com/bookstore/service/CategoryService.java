package com.bookstore.service;

import com.bookstore.bean.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${邹} on 2018/9/11.
 */
@Repository
public interface CategoryService {
    // 查询所有
    List<Category> select();
    // 增
    int insert(Category category);
    // 删
    int deleteById(Long id);
    // 改
//    int updateById(Category category,Long... id);
    int updateById(Category category);

}
