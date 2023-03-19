package com.example.xssdemo.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private UUID id;
    private String username;
    private String name;
    private String role;
    private Date createdAt;
    private Date updatedAt;
}
