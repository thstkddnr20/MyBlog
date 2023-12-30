package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.Articles;
import com.example.MyBlog.Domain.Comments;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @EntityGraph(attributePaths = {"reply"})
    @Query("select c from Comments c where c.articles = :article")
    List<Comments> findCommentsWithReply(@Param("article") Articles articles); // 특정 article의 댓글과 대댓글을 모두 가져옴.
}
