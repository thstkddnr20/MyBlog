package com.example.MyBlog.Api;

import com.example.MyBlog.Domain.ArticleShow;
import com.example.MyBlog.Domain.Articles;
import com.example.MyBlog.Dto.ArticlesCreateDto;
import com.example.MyBlog.Dto.ArticlesWithTagResponse;
import com.example.MyBlog.Service.ArticlesService;
import com.example.MyBlog.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
public class ArticlesApiController {

    private final ArticlesService articlesService;
    private final MemberService memberService;
    @PostMapping("/new")// 게시물 등록
    public ResponseEntity<Articles> createArticles(@RequestBody ArticlesCreateDto Dto){

        ArticleShow articleShow = Dto.isShow() ? ArticleShow.Y : ArticleShow.N; // boolean으로 ArticleShow를 바꿈

        if (Dto.getTags().isEmpty()) {
            Articles articles = new Articles(Dto.getThumbnail(), Dto.getContent(), articleShow, memberService.splitNameAndTag(Dto.getTagAndName()));
            articlesService.createArticlesWithTag(articles);
            return ResponseEntity.status(HttpStatus.CREATED).body(articles);
        }
        else {
            Articles articles = new Articles(Dto.getThumbnail(), Dto.getContent(), articleShow, memberService.splitNameAndTag(Dto.getTagAndName()),Dto.getTags());
            articlesService.createArticlesWithTag(articles);
            return ResponseEntity.status(HttpStatus.CREATED).body(articles);
        }

    }

    @GetMapping("/find/{tagName}")// 특정 tag이름으로 게시물 찾기
    public ResponseEntity<List<ArticlesWithTagResponse>> findArticlesWithTag(@PathVariable String tagName){


        List<Articles> articlesByTag = articlesService.findArticlesByTag(tagName);

        List<ArticlesWithTagResponse> toDto = articlesByTag
                .stream()
                .map(t -> new ArticlesWithTagResponse(t.getThumbnail(), t.getContent()))
                .toList();

        return ResponseEntity.ok().body(toDto);

    }
}
