package com.bookstore.service;

import com.bookstore.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2018/9/11.
 */
public interface UserService {

	int insert(User user);

	User selectById(Long id);

	User selectByUname(String uname);

	int updateById(User user);

	int deleteById(Long... id);
}