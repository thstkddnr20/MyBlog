package com.example.MyBlog.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ArticlesTag {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "articles_id")
    private Articles articles;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public ArticlesTag(Articles articles, Tag tag) {
        this.articles = articles;
        this.tag = tag;
    }

    public ArticlesTag() {
    }
}
