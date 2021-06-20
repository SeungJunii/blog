package com.jun.blog.controller.api;

import com.jun.blog.dto.ResponseDto;
import com.jun.blog.model.User;
import com.jun.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /*@Autowired
    private HttpSession session;*/

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){//username,password,email
        System.out.println("UserApiController : save 호출됨");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        //자바오브젝트를 JSON으로 변환하여 리턴
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
        //key=value, X-www-form-urlencoded
        userService.회원수정(user);
        //여기서는 트랜잭션이 종료되므로 DB값은 변경됨
        //하지만 세션값은 변경되지 않은상태이므로 직접 세션을 변경해야함
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    //
   /* @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
    public ResponseDto<Integer> login(@RequestBody User user){
        System.out.println("UserApiController : login 호출됨");
        User principal = userService.로그인(user); //principal(접근주체)
        if (principal !=null){
            session.setAttribute("principal",principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }*/
}
