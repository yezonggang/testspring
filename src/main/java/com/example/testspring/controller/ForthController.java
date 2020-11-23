package com.example.testspring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForthController {
    @GetMapping("/Students")
    public String getAllStudent( ){
        return "Student";
    }


}
