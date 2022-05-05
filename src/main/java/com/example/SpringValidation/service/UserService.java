package com.example.SpringValidation.service;

import com.example.SpringValidation.dto.UserRequest;
import com.example.SpringValidation.entity.User;
import com.example.SpringValidation.exception.UserNotFoundException;
import com.example.SpringValidation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        try {
            userRepository.deleteById(id);
            return "deleted user id" + id;
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not found with Id :" + id);
        }
    }

    public User updateUserInfo(User user) {

        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAge(user.getAge());
        existingUser.setGender(user.getGender());
        existingUser.setNationality(user.getNationality());
        existingUser.setPhone(user.getPhone());
        return userRepository.save(existingUser);

    }

    public List<User> findPaginated(int pageNo, int pageSize) {

        Sort nameSort = Sort.by("name").ascending();
        Sort ageSort = Sort.by("age").ascending();
        Sort idSort = Sort.by("id").ascending();

        Pageable paging = PageRequest.of(pageNo, pageSize, ageSort.and(idSort));
        Page<User> pageResult = userRepository.findAll(paging);
        return pageResult.toList();


    }

    public List<User> findPaginatedBySort( int pageNo, int pageSize,Sort by) {

        Pageable paging = PageRequest.of(pageNo, pageSize, by);
        Page<User> pagedResult = userRepository.findAll(paging);
        return pagedResult.toList();
    }
}
