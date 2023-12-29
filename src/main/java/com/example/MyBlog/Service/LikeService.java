package com.example.MyBlog.Service;

import com.example.MyBlog.Domain.ArticleLike;
import com.example.MyBlog.Domain.Articles;
import com.example.MyBlog.Domain.Member;
import com.example.MyBlog.Repository.ArticleLikeRepository;
import com.example.MyBlog.Repository.ArticlesRepository;
import com.example.MyBlog.Repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final ArticleLikeRepository articleLikeRepository;

    private final ArticlesRepository articlesRepository;

    private final MemberRepository memberRepository;

    public void plusLike(Long articleId, Long memberId) { //특정 게시물에 특정 member가 좋아요를 누르는 서비스
        Articles articles = articlesRepository.findById(articleId).orElseThrow(()-> new EntityNotFoundException("No Article"));
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new EntityNotFoundException("No Member"));

        //특정 게시물에 특정 멤버의 좋아요가 없는걸 확인하는 if문(이것 때문에 ArticleLike 객체가 따로 있다) -> 새로운 articleLike를 생성 -> 게시물의 좋아요 수를 가져옴 -> 더하기 1
        if (!articleLikeRepository.existsByArticlesAndMember(articles, member)) {
            ArticleLike articleLike = new ArticleLike(member, articles);
            int likes = articles.getLikes();
            articles.setLikes(likes + 1);
            articleLikeRepository.save(articleLike);
        }
        //다시 좋아요를 누르면 취소할 수 있게 else구문을 구현하지 않았다.

    }

    public void deleteLike(Long articleId, Long memberId){
        Articles articles = articlesRepository.findById(articleId).orElseThrow(()-> new EntityNotFoundException("No Article"));
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new EntityNotFoundException("No Member"));

        ArticleLike byArticlesAndMember = articleLikeRepository.findByArticlesAndMember(articles, member);

        if (byArticlesAndMember != null) {
            articleLikeRepository.delete(byArticlesAndMember);
            int likes = articles.getLikes();
            articles.setLikes(likes - 1);
        }

    }

}
