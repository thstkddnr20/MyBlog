package com.example.MyBlog.Service;

import com.example.MyBlog.Domain.*;
import com.example.MyBlog.Repository.ArticlesRepository;
import com.example.MyBlog.Repository.ArticlesTagRepository;
import com.example.MyBlog.Repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class ArticlesService {


    private final ArticlesRepository articlesRepository;
    private final ArticlesTagRepository articlesTagRepository;
    private final TagRepository tagRepository;


    public void createArticles(Articles articles) {
        articlesRepository.save(articles);
    }

    public void createArticlesWithTag(Articles articles) {
        articlesRepository.save(articles);
        Set<String> tags = articles.getTags();

        for (String s : tags) {
            Tag tag = tagRepository.findByName(s);

            if (tag == null) {
                tag = new Tag(s);
                tagRepository.save(tag);
            }

            ArticlesTag articlesTag = new ArticlesTag(articles, tag);
            articlesTagRepository.save(articlesTag);
        }

//        for (String s : tags) {
//            Boolean exists = tagRepository.existsByName(s);
//            if (!exists) {
//                Tag tag = new Tag(s);
//                tagRepository.save(tag);
//                ArticlesTag articlesTag = new ArticlesTag(articles, tag);
//                articlesTagRepository.save(articlesTag);
//            }
//            else {
//                Tag byName = tagRepository.findByName(s);
//                tagRepository.save(byName);
//                ArticlesTag articlesTag = new ArticlesTag(articles, byName);
//                articlesTagRepository.save(articlesTag);
//            }
//        }
    }


    public void updateArticles(Long id, String thumbnail, String content, ArticleShow articleShow, Category category){ // Article 수정
        Articles articlesById = articlesRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("게시물이 없습니다"));

        articlesById.setThumbnail(thumbnail);
        articlesById.setContent(content);
        articlesById.setArticleShow(articleShow);
        articlesById.setA_udt(LocalDateTime.now());
        articlesById.setCategory(category);
    }

    public void deleteArticles(Long id) { // Article 삭제
        articlesRepository.deleteById(id);
    }

    public int searchLikes(Long id) {
        Articles articles = articlesRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("게시물이 없습니다"));
        return articles.getLikes();
    }

    public void findArticles(Long memberId) {
        List<Articles> findArticles = articlesRepository.findAllByMemberId(memberId);
    }



}
