package com.example.MyBlog.Dto;

import lombok.Data;

import java.util.List;

@Data
public class ArticlesCreateDto {
    private String thumbnail;
    private String content;
    private boolean show;
    private String tagAndName;
    private List<String> tags;

    public ArticlesCreateDto(String thumbnail, String content, boolean show, String tagAndName) {
        this.thumbnail = thumbnail;
        this.content = content;
        this.show = show;
        this.tagAndName = tagAndName;
    }

    public ArticlesCreateDto(String thumbnail, String content, boolean show, String tagAndName, List<String> tags) {
        this.thumbnail = thumbnail;
        this.content = content;
        this.show = show;
        this.tagAndName = tagAndName;
        this.getTags().addAll(tags);
    }
    public ArticlesCreateDto() {
    }
}
