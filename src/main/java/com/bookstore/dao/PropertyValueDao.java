package com.bookstore.dao;

import com.bookstore.bean.PropertyValue;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Administrator on 2018/9/21.
 */
@Repository
public interface PropertyValueDao {

	int deleteBatch(Long... id);

	List<PropertyValue> list();

	int updateById(PropertyValue propertyvalue);

	int insert(PropertyValue propertyvalue);

	PropertyValue selectById(Long id);

	PropertyValue get(Long property_id, Long product_id);
}
