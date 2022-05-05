package com.example.SpringValidation.controller;

import com.example.SpringValidation.dto.UserRequest;
import com.example.SpringValidation.entity.User;
import com.example.SpringValidation.exception.UserNotFoundException;
import com.example.SpringValidation.repository.UserRepository;
import com.example.SpringValidation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest userRequest) {
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/fetchUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable int id) throws UserNotFoundException {
        return userService.deleteUser(id);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @GetMapping("/f/{pageNo}/{pageSize}")
    public List<User> getPaginated( @PathVariable int pageNo, @PathVariable int pageSize ){

        return  userService.findPaginated(pageNo,pageSize);
    }

    @GetMapping("/sort/name/{pageNo}/{pageSize}")
    public List<User> getPaginatedBySort(@PathVariable int pageNo, @PathVariable int pageSize){

        Sort nameSort =Sort.by("name").ascending();
        return userService.findPaginatedBySort(pageNo,pageSize,nameSort);
    }

    @GetMapping("/sort/age/{pageNo}/{pageSize}")
    public List<User> getPaginatedBySortAge(@PathVariable int pageNo, @PathVariable int pageSize){

        Sort ageSort =Sort.by("age").ascending();
        return userService.findPaginatedBySort(pageNo,pageSize,ageSort);

    }

    @GetMapping("/sort/id/{pageNo}/{pageSize}")
    public List<User> getPaginatedBySortId(@PathVariable int pageNo, @PathVariable int pageSize){

        Sort idSort =Sort.by("id").ascending();
        return userService.findPaginatedBySort(pageNo,pageSize,idSort);

    }
}
