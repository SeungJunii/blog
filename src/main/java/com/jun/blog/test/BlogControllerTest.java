package com.jun.blog.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogControllerTest {

    @GetMapping("/test/hello")
    public String hello(){
        return "<h1>Hello Spring boot</h1>";
    }
}
