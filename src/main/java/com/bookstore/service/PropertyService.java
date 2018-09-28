package com.bookstore.service;

import com.bookstore.bean.Property;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${邹} on 2018/9/19.
 */
@Repository
public interface PropertyService {
    List<Property> select();
    int deleteById(Long id);
    int updateById(Property property);
    int insert(Property property);
    List<Property> selectById(Long id);
}
