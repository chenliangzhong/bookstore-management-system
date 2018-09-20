package com.bookstore.service.impl;

import com.bookstore.bean.Property;
import com.bookstore.dao.PropertyDao;
import com.bookstore.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${邹} on 2018/9/19.
 */
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyDao propertyDao;
    @Override
    public List<Property> select() {
        return propertyDao.select();
    }
}
