package com.spring.boot.springsecurityproject3.Repository;

import com.spring.boot.springsecurityproject3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);

    User findUserByUsername(String username);
}
