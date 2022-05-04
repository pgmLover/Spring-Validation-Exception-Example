package com.example.SpringValidation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "username shouldn't be null")
    private  String name;

    @Email(message = "Invalid email")
    private  String email;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid PhoneNumber")
    private  String phone;

    private  String gender;

    @Min(18)
    @Max(60)
    private  int age;

    private String nationality;
}
