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

import java.util.List;

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

    }

    @Test
    void CommentsWithReply() {
        Articles articles = new Articles();
        Member member = new Member("memberA","thstkddnr20@naver.com", "pass", MemberRole.USER);

        articles.setThumbnail("a");
        articles.setContent("bbbb");
        articlesRepository.save(articles);
        memberRepository.save(member);

        Comments comments = new Comments(articles, member, "AA", CommentShow.Y);
        commentsRepository.save(comments);

        Comments reply1 = new Comments(articles, member, "nyaha", CommentShow.Y, comments);
        commentsRepository.save(reply1);

        List<Comments> commentsWithReply = commentsRepository.findCommentsWithReply(articles);
        System.out.println("commentsWithReply size = " + commentsWithReply.size());


    }
}