package com.example.MyBlog.Service;

import com.example.MyBlog.Domain.Articles;
import com.example.MyBlog.Domain.Member;
import com.example.MyBlog.Repository.ArticleLikeRepository;
import com.example.MyBlog.Repository.ArticlesRepository;
import com.example.MyBlog.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class LikeServiceTest {

    @Autowired ArticleLikeRepository articleLikeRepository;

    @Autowired ArticlesRepository articlesRepository;

    @Autowired MemberRepository memberRepository;

    @Autowired
    LikeService likeService;

    @Test
    public void plusLike() throws Exception{
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

        System.out.println("articles.getLikes() = " + articles.getLikes());

        likeService.plusLike(articles.getId(), member.getId());
        likeService.plusLike(articles.getId(), member1.getId());
        likeService.plusLike(articles.getId(), member.getId());

        likeService.deleteLike(articles.getId(), member1.getId());



    }

}