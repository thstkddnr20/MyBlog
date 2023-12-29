package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.Articles;
import com.example.MyBlog.Domain.Comments;
import com.example.MyBlog.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Comments findCommentsByArticlesAndMemberOrderByIdDesc(Articles articles, Member member);
}
