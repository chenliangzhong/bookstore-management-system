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
    public List<Product> select() {
        return productDao.select();
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
        return productDao.deleteBatch(id);
    }
}