package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.Member;
import com.example.MyBlog.Service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;


    @Test
    public void testFindAllFriends() {

        Member member1 = new Member();
        memberService.createMember(member1);
        System.out.println("member1.getId() = " + member1.getId());



//        List<Member> member1FriendList = memberRepository.findAllFriendsById(member1.getId());
//        System.out.println("member1friendList = " + member1FriendList);
//
//        member1.removeFriend(member2);
//        List<Member> member1FriendList2 = memberRepository.findAllFriendsById(member1.getId());
//        System.out.println("member1friendList2 = " + member1FriendList2);
    }
}