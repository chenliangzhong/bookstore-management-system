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
@RequestMapping("/category")
public class CategoryController extends BaseApiController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size)
    {
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<Category>( categoryService.select() ) );
    }

    @PostMapping("/add")
    public Map<String,Object> add(@RequestParam String theory, @RequestParam String philosophy,
                                  @RequestParam String sciences, @RequestParam String law,
                                  @RequestParam String military){
        if (theory == null || theory.trim().length() == 0)return onBadResp( "马克思主义不能为空" );
        if (philosophy == null || philosophy.trim().length() == 0)return onBadResp( "哲学、宗教不能为空" );
        if (sciences == null || sciences.trim().length() == 0)return onBadResp( "社会科学总论不能为空" );
        if (law == null || law.trim().length() == 0)return onBadResp( "政治、法律不能为空" );
        if (military == null || military.trim().length() == 0)return onBadResp( "军事不能为空" );
        Category category = new Category();
        category.setTheory( theory );
        category.setPhilosophy( philosophy );
        category.setSciences( sciences );
        category.setLaw( law );
        category.setMilitary( military );
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
    public Map<String,Object> update(@RequestParam Long id,@RequestParam String theory,
                                     @RequestParam String philosophy,@RequestParam String sciences,
                                     @RequestParam String law,@RequestParam String military){
        Category category = new Category();
        System.out.println( id );
        System.out.println( theory );
        System.out.println( philosophy );
        System.out.println( sciences );
        System.out.println( law );
        System.out.println( military );
        category.setId( id );
        if (theory != null)category.setTheory( theory.trim() );
        if ( philosophy != null)category.setPhilosophy( philosophy.trim() );
        if (sciences != null)category.setSciences( sciences.trim() );
        if (law != null)category.setLaw( law.trim() );
        if (military != null)category.setMilitary( military.trim() );
        categoryService.updateById( category);
        return onSuccessRep( "修改成功" );
    }


}
