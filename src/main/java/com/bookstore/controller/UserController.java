package com.bookstore.controller;

import com.bookstore.bean.MyPageInfo;
import com.bookstore.bean.Password;
import com.bookstore.bean.User;
import com.bookstore.common.UserManager;
import com.bookstore.enums.RoleTypeEnum;
import com.bookstore.util.Md5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import com.bookstore.service.UserService;
import com.bookstore.util.RSAUtils;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController extends  BaseApiController{

    @Autowired
    private UserService userService;

    @PostMapping("/pubkey")
    public Map<String, Object> pubkey(HttpSession session) {
        Map<String, Object> keypair = RSAUtils.getKey();
        session.setAttribute(RSAUtils.KEYPAIR, keypair);

        RSAPublicKey publicKey = (RSAPublicKey) keypair.get(RSAUtils.PUBLIC_KEY);

        String modulus = publicKey.getModulus().toString(16);
        String exponent = publicKey.getPublicExponent().toString(16);

        Map<String, Object> param = new HashMap<>();
        param.put("modulus", modulus);
        param.put("exponent", exponent);

        return onDataResp(param);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String mobile_phone, @RequestParam String password, HttpSession session) {
        if (mobile_phone == null || mobile_phone.trim().length() == 0) return onBadResp ("手机号不能为空");
        if (password == null || password.trim().length() == 0) return onBadResp ("密码不能为空");
        Password pwd = null;

        try{
            Map<String, Object> keypair = (Map<String, Object>) session.getAttribute(RSAUtils.KEYPAIR);
            session.removeAttribute(RSAUtils.KEYPAIR);

            if (keypair != null) pwd = new Gson().fromJson(RSAUtils.decrypt((RSAPrivateKey) keypair.get(RSAUtils.PRIVATE_KEY), password), Password.class);
        }catch (Exception e) {
        }
        if (pwd == null) return onBadResp("密码错误");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(mobile_phone.trim(),pwd.getPassword());

        try {
            // 登录，即身份验证
            subject.login(token);

            return onSuccessRep("登录成功");
        } catch (UnknownAccountException e) {
            return onBadResp("用户名或密码错误");
        } catch (IncorrectCredentialsException e) {
            return onBadResp("用户名或密码错误");
        } catch (LockedAccountException e) {
            return onBadResp("登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return onBadResp("验证失败");
        }
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam String mobile_phone, @RequestParam String password, @RequestParam(defaultValue = "") String email) {
        if (mobile_phone == null || mobile_phone.trim().length() == 0) return onBadResp("手机号不能为空");
        if (password == null || password.trim().length() == 0) return onBadResp("密码不能为空");

        String regex = "1[3578][0-9]{9}";
        boolean b = mobile_phone.matches(regex);

        if (b) {
            User user = new User();
            user.setMobile_phone(mobile_phone);
            user.setPassword(Md5Utils.encrypt(mobile_phone.trim(), password.trim()));
            user.setUsername(mobile_phone);
            user.setEmail(email);
            user.setRoleTypeEnum(RoleTypeEnum.USER);
            if (userService.selectByUname(user.getUsername()) != null ) return onBadResp(user.getUsername() + "已经存在，不能重复注册");
            userService.insert(user);
            return onRespWithId("注册成功", user.getId());
        }else {
            return onBadResp("请输入正确的手机号格式");
        }
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

    //修改资料接口
    @PostMapping("/update")
    public Map<String, Object> update(String username, String email, HttpServletRequest request){
        if (username == null || username.trim().length() == 0) return onBadResp("用户名不能为空");

        User currentUser = UserManager.getUser(request);

        User user = new User();
        user.setId(currentUser.getId());
        if (username != null) user.setUsername(username.trim());
        if (email != null) user.setEmail(email.trim());
        userService.updateById(user);

        UserManager.setUser(userService.selectById(currentUser.getId()), request);
        return onSuccessRep("修改成功");
    }

    //修改密码接口  ok
    @PostMapping("/update/pwd")
    public Map<String, Object> alterPw(@RequestParam String password, @RequestParam String new_password, @RequestParam String re_password,  HttpServletRequest request) {
        if (password == null || password.trim().length() == 0) return onBadResp ("原密码长度必须大于零");
        if (new_password == null || new_password.trim().length() == 0) return onBadResp ("新密码长度必须大于零");
        if (new_password.equals(re_password)) {
            User user = userService.selectById(UserManager.getUser(request).getId());
            if (user == null || !user.getPassword().equals(Md5Utils.encrypt(user.getMobile_phone(), password.trim())))
                return onBadResp("密码修改失败");

            User updateUser = new User();
            updateUser.setId(user.getId());
            updateUser.setPassword(Md5Utils.encrypt(user.getMobile_phone(), new_password.trim()));
            userService.updateById(updateUser);
            return onSuccessRep(" 密码修改成功");
        }else {
            return onBadResp("密码不一致");
        }
    }

    @GetMapping("/current")
    public Map<String,Object> show(HttpServletRequest request) {
        return onDataResp(userService.selectById(UserManager.getUser(request).getId()));
    }

    @GetMapping("/select")
    public Map<String, Object> select(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size,@RequestParam Long id){
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<User>(userService.select(id)));
    }
    @GetMapping("/show/{id}")
    public Map<String, Object> selectById(@PathVariable Long id){
        return onDataResp(userService.selectById(id));
    }
}
