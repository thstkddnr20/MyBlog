package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.ArticleLike;
import com.example.MyBlog.Domain.Articles;
import com.example.MyBlog.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
    boolean existsByArticlesAndMember(Articles article, Member member);

    ArticleLike findByArticlesAndMember(Articles articles, Member member);
}
