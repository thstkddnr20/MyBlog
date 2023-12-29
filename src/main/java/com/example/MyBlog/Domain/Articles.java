package com.example.MyBlog.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Articles {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String thumbnail;

    @NotNull
    private String content;

    @Enumerated(EnumType.STRING)
    private ArticleShow articleShow = ArticleShow.Y;

    private LocalDateTime a_udt;

    private LocalDateTime a_cdt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int likes;

    @Transient
    private Set<String> tags = new HashSet<>(); // 임시로 tags에 넣어놓는다. Transient를 사용하여 영속성 컨텍스트에 저장되지 않게함

    public Articles(String thumbnail, String content, ArticleShow articleShow, Member member) {
        this.thumbnail = thumbnail;
        this.content = content;
        this.articleShow = articleShow;
        this.member = member;
    }

    public Articles(String thumbnail, String content, ArticleShow articleShow, Member member, String... tagName) { //가변인자로 tag를 받는다.
        this.thumbnail = thumbnail;
        this.content = content;
        this.articleShow = articleShow;
        this.member = member;
        for (String s : tagName) {
            getTags().add(s);
        }
    }

    public Articles() {
    }


}
