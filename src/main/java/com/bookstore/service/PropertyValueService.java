package com.bookstore.service;

import com.bookstore.bean.PropertyValue;
import java.util.List;

/**
 * Created by Administrator on 2018/9/21.
 */
public interface PropertyValueService {

	int updateById(PropertyValue propertyValue);

	int deleteBatch(Long... id);

	List<PropertyValue> list();

	int insert(PropertyValue propertyValue);
}
