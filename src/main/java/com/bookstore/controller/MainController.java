package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController extends BaseController {
    @GetMapping("/explain/{page}")
    public String explainpage(@PathVariable String page) {
        return "/explain/" + page;
    }

    @GetMapping("/index/{page}")
    public String Indec(@PathVariable String page) {return page;}
}
