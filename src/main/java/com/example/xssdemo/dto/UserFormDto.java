package com.example.xssdemo.dto;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserFormDto {
    
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "invalid name")
    private String name;

    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w]|.*[_]).{6,}$", 
        message = "invalid password"
    )
    private String password;
    
    @Pattern(regexp = "^[a-zA-Z]\\w{4,29}$", message = "invalid username")
    private String username;

    private String role;
}
