package com.jun.blog.test;

import com.jun.blog.model.RoleType;
import com.jun.blog.model.User;
import com.jun.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired // DI
    private UserRepository userRepository;

//    http://localhost:8000/blog/dummy/user/{id}
    //save 함수는 id를 전달하지 않으면 insert를 해주고
    //id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
    //id를 전달하면 해당 id에 대한 데이터가 없으면 insert
    //email,password
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User requestUser){
        System.out.println("id : "+id);
        System.out.println("password : "+requestUser.getPassword());
        System.out.println("email : "+requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정 실패");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

       //userRepository.save(user);
        
        //더티 체킹
        return null;

    }

    //http://localhost:8000/blog/dummy/users
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }
    //http://localhost:8000/blog/dummy/user
    //한페이지당 2건의 데이터를 리턴
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable)
    {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    //http://localhost:8000/blog/dummy/user/3(요청)
    //{id} 주소로 파라미터를 전달 받을 수 있음.
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){

//        람다식
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자는 없습니다");
//        });

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
            }
        });
        return user;
    }

    //http://localhost:8000/blog/dummy/join(요청)
    //http의 body에 username, password, email 데이터를 가지고(요청)
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id : "+user.getId());
        System.out.println("username : "+user.getUsername());
        System.out.println("password : "+user.getPassword());
        System.out.println("email : "+user.getEmail());
        System.out.println("role : "+user.getRole());
        System.out.println("createDate : "+user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }

}
