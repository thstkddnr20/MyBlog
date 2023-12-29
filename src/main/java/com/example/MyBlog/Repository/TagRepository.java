package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Boolean existsByName(String name);

    Tag findByName(String name);
}
