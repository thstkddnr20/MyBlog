package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.Friend;
import com.example.MyBlog.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Optional<Friend> findByMemberAndFriend(Member member1, Member member2);
}
