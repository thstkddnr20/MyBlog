package com.example.MyBlog.Service;

import com.example.MyBlog.Domain.*;
import com.example.MyBlog.Repository.ArticlesRepository;
import com.example.MyBlog.Repository.CommentsRepository;
import com.example.MyBlog.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CommentsServiceTest {

    @Autowired
    CommentsService commentsService;

    @Autowired
    ArticlesRepository articlesRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @Test
    void createComments() {

        Articles articles = new Articles();
        Member member = new Member();
        Member member1 = new Member();

        articles.setThumbnail("a");
        articles.setContent("bbbb");
        articlesRepository.save(articles);

        member.setName("memberA");
        member.setEmail("thstkddnr20@naver.com");
        member.setPassword("pass");
        memberRepository.save(member);
        memberRepository.save(member1);

        Comments comments = commentsService.createComments(articles.getId(), member, "AA", CommentShow.Y);
        commentsService.updateComments(comments.getId(), "BB", CommentShow.N);

    }

    @Test
    void updateComments() {
    }
}