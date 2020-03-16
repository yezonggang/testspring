package com.example.testspring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//返回页面

/*
 通过url：http://localhost:8080/first
 访问 first.html
 */

@Controller
public class FirstController {
    @GetMapping("/first")
    public String first(){
        return "first";
    }

}
