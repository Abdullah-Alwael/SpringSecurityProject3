package com.spring.boot.springsecurityproject3.Service;

import com.spring.boot.springsecurityproject3.Api.ApiException;
import com.spring.boot.springsecurityproject3.Model.User;
import com.spring.boot.springsecurityproject3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addUser(User user){

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> getAllUsers(){ // for admin only
        return userRepository.findAll();
    }

    public User getUser(Integer userId){
        return userRepository.findUserById(userId);
    }

    public void updateUser(Integer userId, User user){
        User oldUser = getUser(userId);

        if(oldUser == null){
            throw new ApiException("Error, user does not exist");
        }

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userRepository.save(oldUser);
    }

    public void deleteUser(Integer userId){
        User oldUser = getUser(userId);

        if(oldUser == null){
            throw new ApiException("Error, user does not exist");
        }

        userRepository.delete(oldUser);
    }

}
