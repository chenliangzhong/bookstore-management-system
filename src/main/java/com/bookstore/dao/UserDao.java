package com.bookstore.dao;

import com.bookstore.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    int insert(User user);

    List<User> list();

    List<User> select (Long id);

    User selectById(Long id);

    User selectByUname(String username);

    int updateById(User user); // 通过id进行修改

    int deleteById(Long... id);
}
