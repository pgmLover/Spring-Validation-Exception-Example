package com.example.SpringValidation.repository;

import com.example.SpringValidation.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

}
