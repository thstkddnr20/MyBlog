package com.example.MyBlog.Dto;

import com.example.MyBlog.Domain.Member;
import lombok.Getter;

@Getter
public class ArticlesWithTagResponse {
    private final String thumbnail;
    private final String content;

    public ArticlesWithTagResponse(String thumbnail, String content) {
        this.thumbnail = thumbnail;
        this.content = content;
    }
}
