package com.bookstore.dao;

import com.bookstore.bean.Propertyvalue;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Administrator on 2018/9/21.
 */
@Repository
public interface PropertyvalueDao {
	int deleteBatch(Long... id);

	List<Propertyvalue> list();

	int updateById(Propertyvalue propertyvalue);

	int insert(Propertyvalue propertyvalue);

}
