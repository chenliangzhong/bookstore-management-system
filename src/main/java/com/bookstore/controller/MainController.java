package com.bookstore.controller;

import com.bookstore.annotation.SystemControllerLog;
import com.bookstore.common.UserManager;
import com.bookstore.enums.RoleType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController extends BaseController {
    @GetMapping("/explain/{page}")
    public String explainpage(@PathVariable String page) {
        return "/explain/" + page;
    }

    @GetMapping("/index/{page}")
    public String Indec(@PathVariable String page) {return page;}

    @GetMapping("/admin/{page}")
    public String admin(@PathVariable String page) {return "/admin/" + page;}

    @GetMapping("/bookstoreHome/{page}")
    public String bookstoreHome(@PathVariable String page) {return "/bookstoreHome/" + page;}

    @GetMapping("/register")
    @SystemControllerLog("注册页面")
    public String register() {
        return "/register";
    }

    @GetMapping("/login")
    @SystemControllerLog("登录页面")
    public String login() {
        return "/login";
    }

    @GetMapping(value = {"/home"})
    @SystemControllerLog("主页面")
    @RequiresRoles(value = {RoleType.USER,RoleType.ADMIN}, logical = Logical.OR)
    public String mainPage(HttpServletRequest request, Model model) {
        return getPageURI(request, model);
    }

    @GetMapping("/admin/{foreign_key}/{page}")
    public String categorySelectProperty(@PathVariable Long foreign_key, @PathVariable String page, Model model) {
        model.addAttribute("foreign_key", foreign_key);
        return "/admin/" + page;
    }

    @GetMapping("/bookstoreHome/{foreign_key}/{page}")
    public String SelectByForeignKey(@PathVariable Long foreign_key, @PathVariable String page, Model model) {
        model.addAttribute("foreign_key", foreign_key);
        return "/bookstoreHome/" + page;
    }

    private String getPageURI(String uri, HttpServletRequest request, Model model) {
//        uri = uri == null ? (request.getRequestURI() + "_include") : uri + "_include";
        model.addAttribute("currentUser", request.getSession().getAttribute(UserManager.CURRENT_USER));
//        model.addAttribute("includePage", uri + ".html");

        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(RoleType.ADMIN)) {
            System.out.println("admin");
            return "/admin_home";
        }else if (subject.hasRole(RoleType.USER)) {
            System.out.println("user");
            return "/index";
        }
        return "/error";
    }

    private String getPageURI(HttpServletRequest request, Model model) {
        return getPageURI(null, request, model);
    }
}
