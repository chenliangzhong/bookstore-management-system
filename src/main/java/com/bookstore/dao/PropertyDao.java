package com.bookstore.dao;

import com.bookstore.bean.Property;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${邹} on 2018/9/19.
 */
@Repository
public interface PropertyDao {
    // 查询属性
    List<Property> select();
}
