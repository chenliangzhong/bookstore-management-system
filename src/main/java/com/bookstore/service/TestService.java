package com.bookstore.service;

import com.bookstore.bean.Test;

import java.util.List;

public interface TestService {

    List<Test> select();

    int updateById(Test test);

    int insert(Test test);
}
