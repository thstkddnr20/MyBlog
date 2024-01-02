package com.example.MyBlog.Dto;

import lombok.Data;

@Data
public class MemberCreateDto {
    private String name;
    private String email;
    private String password;
}
