package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.PropertyValue;
import com.bookstore.service.PropertyValueService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/21.
 */
@RestController
@RequestMapping("/api/propertyValue")
public class PropertyValueController extends BaseApiController{

	@Autowired
	PropertyValueService propertyValueService;

	@PostMapping("/delete")
	public Map<String, Object> delete(@RequestParam Long[] id) {
		propertyValueService.deleteBatch(id);
		return onSuccessRep("删除成功");
	}

	@GetMapping("/list")
	public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size){
		PageHelper.startPage(page_num, page_size);
		return onDataResp(new MyPageInfo<PropertyValue>(propertyValueService.list()));
	}

	@PostMapping("/add")
	public Map<String, Object> add(@RequestParam String value, @RequestParam Long product_id,@RequestParam Long property_id ) {

		if (value == null || value.trim().length() == 0) return onBadResp("vlue 不能为空");
		if (product_id == null) return onBadResp("product_id 不能为空");
		if (property_id == null) return onBadResp("property_id 不能为空");
		PropertyValue propertyvalue=new PropertyValue();
		propertyvalue.setValue(value.trim());
		propertyvalue.setProductId(product_id);
		propertyvalue.setPropertyId(property_id);

		if (propertyValueService.insert(propertyvalue) > 0) return onSuccessRep("添加成功");
		return onBadResp("添加失败");
	}

	// 改(不成功)
	@PostMapping("/update")
	public Map<String, Object> update(@RequestParam Long id, @RequestParam String value, @RequestParam Long product_id, @RequestParam Long property_id
	)
	{
		if (value != null && value.trim().length() == 0) return onBadResp("");
		PropertyValue propertyvalue = new PropertyValue();
		propertyvalue.setId(id);
		if (value != null) propertyvalue.setValue(value.trim());
		if (product_id != null) propertyvalue.setProductId(product_id);
		if (property_id != null) propertyvalue.setPropertyId(property_id);

		if (propertyValueService.updateById(propertyvalue) > 0){onSuccessRep("修改成功");}
		return onSuccessRep("修改失败");
	}

}
