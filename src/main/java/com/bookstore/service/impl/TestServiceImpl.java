package com.bookstore.service.impl;

import com.bookstore.bean.Test;
import com.bookstore.dao.TestDao;
import com.bookstore.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> select() {
        return testDao.select();
    }

    @Override
    public int updateById(Test test) {
        return testDao.updateById(test);
    }

    @Override
    public int insert(Test test) {
        return testDao.insert(test);
    }
}
