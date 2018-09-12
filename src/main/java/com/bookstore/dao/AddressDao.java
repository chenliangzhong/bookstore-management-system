package com.bookstore.dao;

import com.bookstore.bean.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by heanxing on 2018/9/11.
 */
@Repository
public interface AddressDao {
    int insert(Address address);

    Address selectById(Long id);

    List<Address> list();

    int updateById(Address address);

    List<Address> selectByUserId(Long user_id);

    int deleteById(Long id);
}
