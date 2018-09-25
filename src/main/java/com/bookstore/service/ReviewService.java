package com.bookstore.service;

import com.bookstore.bean.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${邹} on 2018/9/25.
 */
@Repository
public interface ReviewService {
    List<Review> select();

    int deleteById(Long id);

    int updateById(Review review);

    int insert(Review review);
}
