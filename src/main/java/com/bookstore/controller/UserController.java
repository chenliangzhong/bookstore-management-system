package com.bookstore.controller;

/**
 * Created by Administrator on 2018/9/11.
 */

import com.bookstore.annotation.SystemControllerLog;
import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.User;
import com.bookstore.enums.UserSex;
import com.bookstore.exception.BadRequestException;
import com.bookstore.service.UserService;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class
UserController extends BaseApiController {

	@Autowired
	UserService userService;

	//登录接口ok
	@PostMapping("/login")
	public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
		if (username == null) return onBadResp("name 不能为空");
		if (password == null || password.trim().length() == 0) return onBadResp("password 不能为空");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User user1 = userService.selectByUname(username);
		if (user1 != null && user1.getPassword(password).equals(password.trim()))
			return onSuccessRep("登录成功");
		throw new BadRequestException("账号或密码错误");

	}

	// 注册接口
	@RequestMapping("/register")
	public Map<String, Object> register(@RequestParam String username, @RequestParam String password,
										@RequestParam String phone, @RequestParam String realname,
										@RequestParam UserSex sex, @RequestParam(defaultValue = "") String address,
										@RequestParam(defaultValue = "") String email) {
		if (username == null || username.trim().length() == 0) return onBadResp("用户名不能为空");
		if (password == null || password.trim().length() == 0) return onBadResp("密码不能为空");
		if (realname == null || realname.trim().length() == 0) return onBadResp("真实姓名不能为空");
		if (address == null) return onBadResp("地址不能为空");
		if (email == null) return onBadResp("邮箱不能为空");

		User user = new User();
		user.setUsername(username.trim());
		user.setPassword(password.trim());
		user.setPhone(phone.trim());
		user.setRelname(realname.trim());
		user.setSex(sex);
		user.setAddress(address.trim());
		user.setEmail(email.trim());
		if (userService.selectByUname(user.getUsername()) != null) return onBadResp(user.getUsername() + " 已经存在，不能重复注册");
		userService.insert(user);
		return onRespWithId("注册成功", user.getId());
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


	//分页显示,用id查询用户
	@GetMapping("/selectBymain")
	public MyPageInfo<User> selectBymain(@RequestParam(required = true, defaultValue = "1") Integer pageNo, @RequestParam(required = true, defaultValue = "2") Integer pageSize, Long id) {
		PageHelper.startPage(pageNo, pageSize);
		return new MyPageInfo<User>(userService.selectById((id)));
	}


	@GetMapping("/show")
	public Map<String,Object> show(@PathVariable Long id) {
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

}


