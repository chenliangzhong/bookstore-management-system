package com.bookstore.service.impl;

import com.bookstore.bean.Address;
import com.bookstore.dao.AddressDao;
import com.bookstore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by heanxing on 2018/9/11.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;
    @Override
    public int insert(Address address) {
        return addressDao.insert(address);
    }

    @Override
    public Address selectById(Long id) {
        return addressDao.selectById(id);
    }

    @Override
    public List<Address> list() {
        return addressDao.list();
    }

    @Override
    public List<Address> selectByUserId(Long user_id) {
        return addressDao.selectByUserId(user_id);
    }


    @Override
    public int updateById(Address address) {
        return addressDao.updateById(address);
    }

    @Override
    public int deleteById(Long id) {
        return addressDao.deleteById(id);
    }


}
