package com.example.SpringValidation.repository;

import com.example.SpringValidation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
