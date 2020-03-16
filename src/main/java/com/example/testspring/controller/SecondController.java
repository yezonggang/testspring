package com.example.testspring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//根据传参，h5页面显示名称

/*
通过URL:http://localhost:8080/second?name=yzg
访问second.html页面，并将yzg传参
*/
@Controller
public class SecondController {
    @GetMapping("/second")
    public String second(@RequestParam(name="name") String name, Model model){
        model.addAttribute("name",name);
        return "second";
    }
}
