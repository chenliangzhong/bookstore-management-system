package com.bookstore.dao;

import com.bookstore.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/9/11.
 */
public interface UserDao {

	int insert(User student);

	User selectById(Long id);

	User selectByUname(String uname);

	int updateById(User student); // 通过id进行修改

	int deleteById(Long... id);
}
