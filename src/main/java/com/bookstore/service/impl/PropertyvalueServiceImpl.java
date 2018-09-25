package com.bookstore.service.impl;

import com.bookstore.bean.Product;
import com.bookstore.bean.Propertyvalue;
import com.bookstore.dao.PropertyvalueDao;
import com.bookstore.service.PropertyvalueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by Administrator on 2018/9/21.
 */
@Service
public class PropertyvalueServiceImpl implements PropertyvalueService {
	@Autowired
	PropertyvalueDao propertyvalueDao;

	@Override
	public int updateById(Propertyvalue propertyvalue) {
		if (propertyvalue == null) return 0;
		return propertyvalueDao.updateById(propertyvalue);
	}

	@Override
	public int deleteBatch(Long... id) {
		return propertyvalueDao.deleteBatch(id);
	}

	@Override
	public List<Propertyvalue> list() {
		return propertyvalueDao.list();
	}

	@Override
	public int insert(Propertyvalue propertyvalue) {
		return propertyvalueDao.insert(propertyvalue);
	}

}
