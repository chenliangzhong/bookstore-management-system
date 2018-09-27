package com.bookstore.service.impl;

import com.bookstore.bean.User;
import com.bookstore.dao.UserDao;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public List<User> select(Long id) {
        return userDao.select(id);
    }


    @Override
    public User selectById(Long id) {
        return userDao.selectById(id);
    }

    @Override
    public User selectByUname(String uname) {
        return userDao.selectByUname(uname);
    }

    @Override
    public int updateById(User user) {
        return userDao.updateById(user);
    }

    @Override
    public int deleteById(Long... id) {
        return userDao.deleteById(id);
    }
}
