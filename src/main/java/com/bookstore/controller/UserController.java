package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.User;
import com.bookstore.exception.BadRequestException;
import com.bookstore.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController extends  BaseApiController{

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String mobile_phone, @RequestParam String password) {
        if (mobile_phone == null || mobile_phone.trim().length() == 0) return onBadResp ("手机号不能为空");
        if (password == null || password.trim().length() == 0) return onBadResp ("密码不能为空");
        User user = new User();
        user.setUsername(mobile_phone);
        user.setPassword(password);
        if (userService.selectByUname(mobile_phone) != null && userService.selectByUname(mobile_phone).getPassword().equals(password.trim()))
            return onSuccessRep("登录成功");
        throw new BadRequestException("账号或密码错误");
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam String mobile_phone, @RequestParam String password, @RequestParam String email) {
        if (mobile_phone == null || mobile_phone.trim().length() == 0) return onBadResp("用户名不能为空");
        if (password == null || password.trim().length() == 0) return onBadResp("密码不能为空");
        if (email == null || email.trim().length() == 0) return onBadResp("真实姓名不能为空");

        User user = new User();
        user.setMobile_phone(mobile_phone);
        user.setPassword(password);
        user.setUsername(mobile_phone);
        user.setEmail(email);
        if (userService.selectByUname(user.getUsername()) != null ) return onBadResp(user.getUsername() + "已经存在，不能重复注册");
            userService.insert(user);
        return onRespWithId("注册成功", user.getId());
    }

    // 分页显示，查询用户
    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size){
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<User>(userService.list()));
    }

    @GetMapping("/show")
    public Map<String,Object> show(@RequestParam Long id) {
        return onDataResp(userService.selectById(id));
    }

    // 注销登录接口
    @RequestMapping("/logout")
    public Map<String, Object> logout() {
        return onSuccessRep("已退出登录");
    }

    // delect 接口
    @PostMapping("/delete")
    public Map<String,
            Object> delte(@RequestParam Long[] id) {
        userService.deleteById(id);
        return onSuccessRep("删除成功");
    }

    //修改密码接口  ok
    @PostMapping("/alterPw")
    public Map<String, Object> alterPw(@RequestParam Long id, @RequestParam String password) {
        if (id == null) return onBadResp("id 不能为空");
        if (password == null || password.trim().length() == 0) return onBadResp("password 不能为空");
        User student = new User();
        student.setId(id);
        student.setPassword(password);
        if (userService.updateById(student) > 0) return onSuccessRep("修改密码成功");
        throw new BadRequestException("修改密码错误");
    }


    @GetMapping("/select")
    public Map<String, Object> select(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size,@RequestParam Long id){
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<User>(userService.select(id)));
    }
}
