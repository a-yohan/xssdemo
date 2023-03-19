package com.example.xssdemo.dto;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleResponseDto {
    
    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class User {
        private UUID id;
        private String name;
    }

    private UUID id;
    private String title;
    private String content;
    private User user;
    private Date createdAt;
    private Date updatedAt;
}
