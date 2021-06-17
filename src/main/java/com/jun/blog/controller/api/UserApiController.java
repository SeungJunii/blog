package com.jun.blog.controller.api;

import com.jun.blog.dto.ResponseDto;
import com.jun.blog.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController : save 호출됨");
        return new ResponseDto<Integer>(200,1);
    }
}
