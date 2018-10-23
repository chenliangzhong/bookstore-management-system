package com.bookstore.dao;

import com.bookstore.bean.PropertyValue;
import org.apache.ibatis.annotations.Param;
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

	PropertyValue get(@Param("propertyId") Long property_id, @Param("productId") Long product_id);

	List<PropertyValue> selectByProductId(@Param("productId") Long product_id);
}
