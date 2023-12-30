package com.example.MyBlog.Service;

import com.example.MyBlog.Domain.*;
import com.example.MyBlog.Repository.ArticlesRepository;
import com.example.MyBlog.Repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
@Rollback(value = false) // RollBack False사용하여 DB에 띄울 수 있고 쿼리 확인 가능 까먹지말자
class ArticlesServiceTest {

    @Autowired
    ArticlesService articlesService;

    @Autowired
    ArticlesRepository articlesRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CommentsService commentsService;

    @Autowired
    LikeService likeService;

    @Test
    public void 게시물_생성() throws Exception {

        //given
        Member member = new Member();
        Member member1 = new Member();

        member.setName("memberA");
        member.setEmail("thstkddnr20@naver.com");
        member.setPassword("pass");

        Articles articles = new Articles("A", "hi", ArticleShow.Y, member);


        articlesRepository.save(articles);
        memberRepository.save(member);
        memberRepository.save(member1);

        likeService.plusLike(articles.getId(), member.getId());

        //when

        int i = articlesService.searchLikes(articles.getId());
        System.out.println("i = " + i);

        articlesService.findArticles(member.getId());
    }

    @Test
    public void 게시물_생성_with_tag() throws Exception{
        Member member = new Member();
        member.setName("memberA");
        member.setEmail("thstkddnr20@naver.com");
        member.setPassword("pass");

        Articles articles = new Articles("A", "hi", ArticleShow.Y, member, "A", "B");
        Articles articles1 = new Articles("B", "hihi", ArticleShow.Y, member, "A", "B", "C");

        memberRepository.save(member);

        articlesService.createArticlesWithTag(articles);
        articlesService.createArticlesWithTag(articles1);

        List<Articles> c = articlesService.findArticlesByTag("D");
        System.out.println("c = " + c);

    }

}