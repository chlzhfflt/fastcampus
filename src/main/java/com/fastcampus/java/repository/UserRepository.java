package com.fastcampus.java.repository;

import com.fastcampus.java.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> { // <타입, 기본키타입>
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
