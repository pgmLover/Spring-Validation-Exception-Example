package com.example.SpringValidation.service;

import com.example.SpringValidation.dto.UserRequest;
import com.example.SpringValidation.entity.User;
import com.example.SpringValidation.exception.UserNotFoundException;
import com.example.SpringValidation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserRequest userRequest) {
        User user = User.build(0, userRequest.getName(), userRequest.getEmail(), userRequest.getPhone(), userRequest.getGender(), userRequest.getAge(), userRequest.getNationality());
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUser(int id) throws UserNotFoundException {

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User Not found with Id :" + id);
        }


    }

    public String deleteUser(int id) throws UserNotFoundException {
            try{
                userRepository.deleteById(id);
                return "deleted user id" + id;
            }
            catch (Exception exception) {
               throw new UserNotFoundException("User Not found with Id :" + id);
            }
        }

    }
