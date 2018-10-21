package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.Product;
import com.bookstore.bean.Property;
import com.bookstore.bean.PropertyValue;
import com.bookstore.service.ProductService;
import com.bookstore.service.PropertyService;
import com.bookstore.service.PropertyValueService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/21.
 */
@RestController
@RequestMapping("/api/propertyValue")
public class PropertyValueController extends BaseApiController{

	@Autowired
	PropertyValueService propertyValueService;

	@Autowired
	ProductService productService;

	@Autowired
	PropertyService propertyService;

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


	@GetMapping ("/selectById/{id}")
	public Map<String, Object> selectById(@PathVariable Long id) {
		return onDataResp(propertyValueService.selectById( id ));
	}

	@PostMapping("/add")
	public Map<String, Object> add(@RequestParam String value, @RequestParam Long product_id) {

		if (value == null || value.trim().length() == 0) return onBadResp("属性值不能为空");
		if (product_id == null) return onBadResp("product_id 不能为空");

		Product p = productService.selectById(product_id);
		List<Property> properties = propertyService.selectByCategoryId(p.getCategoryId());
		for (Property pt : properties) {
			PropertyValue propertyvalue = propertyValueService.get(pt.getId(),p.getId());
			if(null==propertyvalue) {
				propertyvalue.setValue(value.trim());
				propertyvalue.setProductId(product_id);
				propertyvalue.setPropertyId(pt.getId());
				if (propertyValueService.insert(propertyvalue) > 0) return onSuccessRep("添加成功");
			}
		}
		return onBadResp("添加失败");
	}

	// 改(不成功)
	@PostMapping("/update")
	public Map<String, Object> update(@RequestParam Long id, @RequestParam String value, @RequestParam Long product_id, @RequestParam Long property_id
	)
	{
		if (value != null && value.trim().length() == 0) return onBadResp("值不能为空");
		PropertyValue propertyvalue = new PropertyValue();
		propertyvalue.setId(id);
		if (value != null) propertyvalue.setValue(value.trim());
		if (product_id != null) propertyvalue.setProductId(product_id);
		if (property_id != null) propertyvalue.setPropertyId(property_id);

		if (propertyValueService.updateById(propertyvalue) > 0){return onSuccessRep("修改成功");}
		return onDataResp("修改失败");
	}

}
