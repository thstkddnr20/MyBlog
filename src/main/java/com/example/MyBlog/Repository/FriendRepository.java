package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Friend findByMemberId(Long id);
}
