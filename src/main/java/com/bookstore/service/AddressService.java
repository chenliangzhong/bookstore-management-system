package com.bookstore.service;

import com.bookstore.bean.Address;

import java.util.List;

/**
 * Created by heanxing on 2018/9/11.
 */
public interface AddressService {
    int insert(Address address);

    Address selectById(Long id);

    List<Address> list();

    List<Address> selectByUserId(Long user_id);

    int updateById(Address address);

    int deleteById(Long id);
}
