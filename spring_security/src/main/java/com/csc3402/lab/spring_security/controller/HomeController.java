package com.csc3402.lab.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @ResponseBody
    @GetMapping("/")
    public String index() {
        return "Hello from index";
    }

    @ResponseBody
    @GetMapping("/about")
    public String about() {
        return "About Spring Boot Security";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String admin() {
        return "Admin Page";
    }
}