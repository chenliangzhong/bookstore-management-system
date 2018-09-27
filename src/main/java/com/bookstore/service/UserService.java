package com.bookstore.service;

import com.bookstore.bean.User;

import java.util.List;

public interface UserService {

    int insert(User user);

    List<User> list();

    List<User> select (Long id);

    User selectById(Long id);

    User selectByUname(String uname);

    int updateById(User user); // 通过id进行修改

    int deleteById(Long... id);
}
