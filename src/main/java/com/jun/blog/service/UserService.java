package com.jun.blog.service;

import com.jun.blog.model.RoleType;
import com.jun.blog.model.User;
import com.jun.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public User 회원찾기(String username) {
        User user = userRepository.findByUsername(username).orElseGet(() -> {
            return new User();
        });
        return user;
    }

    @Transactional
    public int 회원가입(User user) {
        String rowPassword = user.getPassword();//1111 원문
        String encPassword = encoder.encode(rowPassword);//해쉬
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Transactional
    public void 회원수정(User user) {
        //수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        //select를 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화하려고
        //영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날림
        User persistence = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("회원 찾기 실패");
        });

        // Validate 체크 => oauth 필드에 값이 없으면 수정 가능
        if (persistence.getOauth() == null || persistence.getOauth().equals("")) {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistence.setPassword(encPassword);
            persistence.setEmail(user.getEmail());
        }
        //회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 됨
        //영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날림
    }
    /*@Transactional(readOnly = true)//select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
    public User 로그인(User user) {
       return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/
}
