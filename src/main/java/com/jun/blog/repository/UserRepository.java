package com.jun.blog.repository;

import com.jun.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//DAO
//자동으로 Bean 등록이 된다.
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User,Integer>{
}
