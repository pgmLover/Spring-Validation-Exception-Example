package com.example.SpringValidation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {


    private  String name;
    private  String email;
    private  String phone;
    private  String gender;
    private  int age;
    private String nationality;
}
