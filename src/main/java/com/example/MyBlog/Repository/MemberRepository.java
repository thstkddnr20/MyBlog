package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByEmail(String email);

    List<Member> findByName(String name);

}
