package com.example.MyBlog.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class ArticleLike {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "articles_id")
    private Articles articles;

    public ArticleLike(Member member, Articles articles) {
        this.member = member;
        this.articles = articles;
    }

    public ArticleLike() {
    }

}
