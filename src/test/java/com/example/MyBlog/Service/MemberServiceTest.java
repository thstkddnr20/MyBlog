package com.example.MyBlog.Service;

import com.example.MyBlog.Domain.Friend;
import com.example.MyBlog.Domain.Member;
import com.example.MyBlog.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceTest {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    void createMember() {
    }

    @Test
    void addFriend() {
        Member member = new Member();
        Member member1 = new Member();
        Member member2 = new Member();


        member.setName("memberA");
        member.setEmail("thstkddnr20@naver.com");
        member.setPassword("pass");
        memberRepository.save(member);
        memberRepository.save(member1);
        memberRepository.save(member2);

        memberService.requestAddFriend(member.getId(), member1.getId());
        memberService.denyAddFriend(member1.getId(), member.getId());

        List<Friend> friendsList = member.getFriendsList();
        System.out.println("friendsList = " + friendsList);


    }
}