package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.Property;
import com.bookstore.service.PropertyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by ${邹} on 2018/9/19.
 */
@RestController
@RequestMapping("/api/property")
public class PropertyController extends BaseApiController {
    @Autowired
    PropertyService propertyService;
    @GetMapping ("/list")
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size) {
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<Property>(propertyService.select()));
    }
    @GetMapping("/delete")
    public Map<String,Object> delete(@RequestParam Long id){
        propertyService.deleteById( id );
        return onSuccessRep( "删除成功" );
    }
    @PostMapping("/update")
    public Map<String,Object> update(@RequestParam Long id,@RequestParam String name,@RequestParam Long category_id){
        Property property = new Property();
        property.setId( id );
        if (name != null)property.setName( name.trim() );
        propertyService.updateById( property);
        return onSuccessRep( "修改成功" );
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam String name, @RequestParam Long category_id) {

        if (name == null || name.trim().length() == 0) return onBadResp("name 不能为空");
        if (category_id == null) return onBadResp("category_id 不能为空");

        Property property = new Property();
        property.setName(name.trim());
        property.setCategory_id(category_id);

        if (propertyService.insert(property) > 0) return onSuccessRep("添加成功");
        return onBadResp("添加失败");
    }

    @GetMapping ("/selectById/{id}")
    public Map<String, Object> selectById(@PathVariable Long id) {
        return onDataResp(propertyService.selectById( id ));
    }

    @GetMapping ("/selectByCategoryId/{category_id}")
    public Map<String, Object> selectByCategoryId(@PathVariable Long category_id) {
        return onDataResp(new MyPageInfo<Property>(propertyService.selectByCategoryId(category_id)));
    }
}
