package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.Articles;
import com.example.MyBlog.Domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {
    List<Articles> findAllByMemberId(Long id);

    @Query("select at.articles from ArticlesTag at where at.tag.name = :name")
    List<Articles> findArticlesByTag(@Param("name") String tagName);

    List<Articles> findArticlesByMember(Member member);
}
