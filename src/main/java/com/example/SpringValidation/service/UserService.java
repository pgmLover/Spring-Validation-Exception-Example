package com.example.SpringValidation.service;

import com.example.SpringValidation.dto.UserRequest;
import com.example.SpringValidation.entity.User;
import com.example.SpringValidation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserRequest userRequest) {
        User user = new User(5, userRequest.getName(), userRequest.getEmail(), userRequest.getPhone(), userRequest.getGender(), userRequest.getAge(), userRequest.getNationality());
        return user;
    }

    public List<User> getAllUser(){
        return  userRepository.findAll();
    }

    public User getUser(int id){
        return  userRepository.findById(id).orElse(null);
    }

}
