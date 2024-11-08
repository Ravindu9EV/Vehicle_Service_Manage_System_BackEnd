package com.icet.crm.repository;

import com.icet.crm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByName(String name);
    User findByEmail(String email);
    User findByEmailAndPassword(String email,String password);

}
