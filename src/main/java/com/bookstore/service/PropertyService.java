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
}
