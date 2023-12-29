package com.example.MyBlog.Service;

import com.example.MyBlog.Repository.ArticlesRepository;
import com.example.MyBlog.Repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final ArticlesRepository articlesRepository;
    private final TagRepository tagRepository;

    public void saveTag(){

    }





}
