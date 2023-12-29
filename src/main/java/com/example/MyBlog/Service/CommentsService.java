package com.example.MyBlog.Service;

import com.example.MyBlog.Domain.*;
import com.example.MyBlog.Repository.ArticlesRepository;
import com.example.MyBlog.Repository.CommentsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final ArticlesRepository articlesRepository;

    public Comments createComments(Long articlesId, Member member, String content, CommentShow commentShow){
        Articles articles = articlesRepository.findById(articlesId).orElseThrow(()-> new EntityNotFoundException("게시물이 없습니다"));

        Comments comments = new Comments(articles, member, content, commentShow);
        commentsRepository.save(comments);
        return comments;
    }

    public void updateComments(Long id, String content, CommentShow commentShow){  // comment를 comment id로 가져와서 갱신시키는 메서드인데,,, 수정필요하겠지?
        Comments comments = commentsRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("댓글이 없습니다"));
        updateMethod(comments, content, commentShow);
    }

    public void deleteComments(Long id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("댓글이 없습니다")); //delete도 id로 함
        commentsRepository.delete(comments);
    }

    private void updateMethod(Comments comments, String content, CommentShow commentShow) {
        comments.setContent(content);
        comments.setCommentShow(commentShow);
        comments.setC_udt(LocalDateTime.now());
    }


}
