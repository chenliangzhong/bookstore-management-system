package com.bookstore.service.impl;

import com.bookstore.bean.Review;
import com.bookstore.dao.ReviewDao;
import com.bookstore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${邹} on 2018/9/25.
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewDao reviewDao;
    @Override
    public List<Review> select() {
        return reviewDao.select();
    }

    @Override
    public int deleteById(Long id) {
        return reviewDao.deleteById( id );
    }

    @Override
    public int updateById(Review review) {
        return reviewDao.updateById( review );
    }

    @Override
    public int insert(Review review) {
        return reviewDao.insert( review );
    }
}
