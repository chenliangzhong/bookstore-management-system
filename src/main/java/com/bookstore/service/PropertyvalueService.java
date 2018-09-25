package com.bookstore.service;

import com.bookstore.bean.Product;
import com.bookstore.bean.Propertyvalue;
import java.util.List;

/**
 * Created by Administrator on 2018/9/21.
 */
public interface PropertyvalueService {

	int updateById(Propertyvalue propertyvalue);

	int deleteBatch(Long... id);

	List<Propertyvalue> list();

	int insert(Propertyvalue propertyvalue);
}
