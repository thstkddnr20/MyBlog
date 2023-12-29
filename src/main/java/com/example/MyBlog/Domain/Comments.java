package com.example.MyBlog.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Comments {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private LocalDateTime c_udt;

    private LocalDateTime c_cdt;

    @Enumerated(EnumType.STRING)
    private CommentShow commentShow;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "articles_id")
    private Articles articles;

    //대댓글 구현 자기 자신에게 양방향 걸기
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_comment")
    private Comments parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comments> reply;

    public Comments(Articles articles, Member member, String content, CommentShow commentShow) {
        this.content = content;
        this.commentShow = commentShow;
        this.member = member;
        this.articles = articles;
        this.c_cdt = LocalDateTime.now();
    }

    public Comments() {
    }
}
