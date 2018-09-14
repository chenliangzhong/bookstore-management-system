package com.bookstore.service.impl;

import com.bookstore.bean.Category;
import com.bookstore.dao.CategoryDao;
import com.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${邹} on 2018/9/11.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;


    @Override
    public List<Category> select() {
        return categoryDao.select();
    }

    @Override
    public int insert(Category category) {
        return categoryDao.insert( category );
    }

    @Override
    public int deleteById(Long id) {
        return categoryDao.deleteById( id );
    }

    @Override
    public int updateById(Category category) {
        return categoryDao.updateById( category );
    }

}
