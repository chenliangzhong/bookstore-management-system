package com.bookstore.dao;

import com.bookstore.bean.Review;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${邹} on 2018/9/25.
 */
@Repository
public interface ReviewDao {
    List<Review> select();

    int deleteById(Long id);

    int updateById(Review review);

    int insert(Review review);

    Review selectByUserIdAndReviewId(@Param("id") Long id, @Param("user_id") Long user_id);

    Review selectById(Long id);
}
