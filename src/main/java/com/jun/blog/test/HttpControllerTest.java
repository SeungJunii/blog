package com.jun.blog.test;


import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {
    private static final String TAG = "HttpControllerTest:";

    @GetMapping("/http/lombok")
    public String lombokTest(){
    Member m = Member.builder().username("sss").password("1234").email("222@222.com").build();
    System.out.println(TAG+"getter : "+m.getUsername());
    m.setUsername("ssa");
    System.out.println(TAG+"setter : "+m.getUsername());
    return "lombok test 완료";
    }
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "get 요청"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
    }

    @PostMapping("/http/post")//text/plain, application/json
    public String postTest(@RequestBody Member m) {//MessageConverter (스프링부트)
        return "post 요청" + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){
        return "put 요청" + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }


    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
