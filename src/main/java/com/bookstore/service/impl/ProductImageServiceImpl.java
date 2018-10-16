package com.bookstore.service.impl;

import com.bookstore.bean.Product;
import com.bookstore.bean.ProductImage;
import com.bookstore.dao.ProductImageDao;
import com.bookstore.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SX-503 on 2018/9/20.
 */
@Service
public class ProductImageServiceImpl implements ProductImageService{

    @Autowired
    ProductImageDao productImageDao;

    @Override
    public int insert(ProductImage productImage) {
        return productImageDao.insert(productImage);
    }

    @Override
    public ProductImage selectById(Long... id) {
        return productImageDao.selectById(id);
    }

    @Override
    public List<ProductImage> show(Long[] product_id) {
        return productImageDao.show(product_id);
    }

    @Override
    public List<ProductImage> selectByProductId(Long id) {
        return productImageDao.selectByProductId(id);
    }

    @Override
    public int updateById(ProductImage productImage) {
        if (productImage == null) return 0;
        return productImageDao.updateById(productImage);
    }

    @Override
    public int deleteBatch(Long... id) {
        return productImageDao.deleteBatch(id);
    }

    @Override
    public ProductImage listByProductId(Long product_id) {
        return productImageDao.listByProductId(product_id);
    }

    @Override
    public ProductImage listById(Long id) {
        return productImageDao.listById(id);
    }

    @Override
    public List<ProductImage> select() {
        return productImageDao.select();
    }
}
