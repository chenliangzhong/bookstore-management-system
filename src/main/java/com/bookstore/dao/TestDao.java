package com.bookstore.dao;

import com.bookstore.bean.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao {

    List<Test> select();
}
