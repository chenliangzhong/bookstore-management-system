package com.bookstore.service.impl;

import com.bookstore.bean.Product;
import com.bookstore.dao.ProductDao;
import com.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SX-503 on 2018/9/19.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public int insert(Product product) {
        return productDao.insert(product);
    }

    @Override
    public Product selectById(Long id) {
        return productDao.selectById(id);
    }

    @Override
    public List<Product> selectByCategoryId(Long category_id) {
        return productDao.selectByCategoryId(category_id);
    }

    @Override
    public List<Product> selectFindProductImg(Long id) {
        return productDao.selectFindProductImg(id);
    }

    @Override
    public List<Product> findImgByCategoryId(Long category_id) {
        return productDao.findImgByCategoryId(category_id);
    }

    @Override
    public Product selectByProductId(Long id) {
        return productDao.selectByProductId(id);
    }


    @Override
    public int updateById(Product product) {
        if (product == null) return 0;
        return productDao.updateById(product);
    }

    @Override
    public int deleteBatch(Long... id) {
        if (id == null || id.length == 0) return 0;
        return productDao.deleteBatch(id);
    }

    @Override
    public List<Product> select() {
        return productDao.select();
    }

    @Override
    public List<Product> selectByName(String name) {
        return productDao.selectByName( name );
    }
}