package com.bookstore.controller;

import com.bookstore.bean.Category;
import com.bookstore.bean.MyPageInfo;
import com.bookstore.service.CategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by ${邹} on 2018/9/11.
 */
@RestController
@RequestMapping("api/category")
public class CategoryController extends BaseApiController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/show")
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size)
    {
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<Category>( categoryService.select() ) );
    }

    @PostMapping("/add")
    public Map<String,Object> add(@RequestParam String name){
        if (name == null || name.trim().length() == 0)return onBadResp( "分类名不能为空" );
        Category category = new Category();
        category.setName( name );
        categoryService.insert( category );
        return onRespWithId( "保存成功",category.getId() );
    }

    @GetMapping("delete")
    public Map<String,Object> delete(@RequestParam Long id){
        categoryService.deleteById( id );
        System.out.println( id );
        return onSuccessRep( "删除成功" );
    }

    @PostMapping("update")
    public Map<String,Object> update(@RequestParam Long id,@RequestParam String name){
        Category category = new Category();
        category.setId( id );
        if (name != null)category.setName( name.trim() );
        categoryService.updateById( category);
        return onSuccessRep( "修改成功" );
    }

    @GetMapping ("/list")
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size, String name) {
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<Category>(categoryService.listByName(name)));
    }

}
