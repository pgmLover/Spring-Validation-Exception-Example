package com.example.SpringValidation.controller;

import com.example.SpringValidation.dto.UserRequest;
import com.example.SpringValidation.entity.User;
import com.example.SpringValidation.exception.UserNotFoundException;
import com.example.SpringValidation.repository.UserRepository;
import com.example.SpringValidation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page getPaginated(@PathVariable int pageNo, @PathVariable int pageSize) {

        return userService.findPaginated(pageNo, pageSize);
    }


    @GetMapping("/getAll/{pageNo}/{pageSize}/{type}/{order}")
    public List<User> getAll(@PathVariable int pageNo, @PathVariable int pageSize,
                             @PathVariable String type, @PathVariable String order) {
        return userService.findPaginatedBySort(pageNo,pageSize,type,order);
    }



    @GetMapping("/Users")
    public  List<User> getAllByFilter(@RequestParam(value = "0") int pageNo, @RequestParam(value ="0")int pageSize,
                                      @RequestParam (value = "id")String type, @RequestParam (value = "ascending()")String order,
                                      @RequestParam (value="search")String search) {

        if (search.equals("search")) {
            return userService.findPaginatedBySort(pageNo, pageSize, type, order);
        }
        else{
            return  userService.findPaginatedByFilter(pageNo, pageSize, type, order,search);
        }


    }



}
