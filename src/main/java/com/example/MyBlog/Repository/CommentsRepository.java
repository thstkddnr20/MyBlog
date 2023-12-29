package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.Articles;
import com.example.MyBlog.Domain.Comments;
import com.example.MyBlog.Domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @EntityGraph(attributePaths = {"reply"})
    @Query("select c from Comments c")
    List<Comments> findCommentsWithReply();
}
