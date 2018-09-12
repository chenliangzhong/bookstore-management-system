package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.Test;
import com.bookstore.service.TestService;
import com.bookstore.util.FileUploadUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController extends BaseApiController {
    @Autowired
    TestService testService;

    @Autowired
    FileUploadUtils fileUploadUtils;

    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size)
    {
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<Test>(testService.select()));
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long id, String username, String password) {
        Test test = new Test();
        if (username != null && username.trim().length() == 0) return onBadResp("姓名不能为空");
        if (password != null && password.trim().length() == 0) return onBadResp("密码不能为空");
        test.setId(id);
        test.setPassword(password);
        test.setUserName(username);
        testService.updateById(test);
        return onSuccessRep("修改成功");
    }

    @PostMapping("/add")
    @Transactional
    public  Map<String, Object> add(@RequestParam(required = false)CommonsMultipartFile file, @RequestParam String username, @RequestParam String password) {
        if (username == null || username.trim().length() == 0) return onBadResp("姓名不能为空");
        if (password == null || password.trim().length() == 0) return onBadResp("密码不能为空");

        Test test = new Test();
        test.setUserName(username);
        test.setPassword(password);

        String filePath = "";


        if (testService.insert(test) > 0) {
            if (StringUtils.isNotEmpty(filePath)) fileUploadUtils.saveFile(file, filePath);
            return onSuccessRep("添加成功");
        }
        return onBadResp("添加失败");
    }

}
