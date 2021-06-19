package com.jun.blog.repository;

import com.jun.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//DAO
//자동으로 Bean 등록이 된다.
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User,Integer>{
    //select * from user where username = 1?;
    Optional<User> findByUsername(String username);
}


//JPA Naming 쿼리
//Select * FROM user WHERE username = ? AND password = ?2;
//    User findByUsernameAndPassword(String username, String password);

//    @Query(value = "Select * FROM user WHERE username = ?1 AND password = ?2;",nativeQuery = true)
//    User login(String username, String password);