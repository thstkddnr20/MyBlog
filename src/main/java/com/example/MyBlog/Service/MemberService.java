package com.example.MyBlog.Service;

import com.example.MyBlog.Domain.Friend;
import com.example.MyBlog.Domain.Member;
import com.example.MyBlog.Repository.FriendRepository;
import com.example.MyBlog.Repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    public void createMember(Member member){ // 회원가입

        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> byEmail = memberRepository.findByEmail(member.getEmail());
        List<Member> byName = memberRepository.findByName(member.getName());

        if (!byEmail.isEmpty() || !byName.isEmpty()){
            throw new IllegalStateException("이메일 또는 이름이 이미 존재합니다");
        }

    }

    public void requestAddFriend(Long id1, Long id2) { //id1 -> id2 친구 추가 신청
        Member member1 = memberRepository.findById(id1).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));
        Member member2 = memberRepository.findById(id2).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));

        Friend friend1 = new Friend();
        friend1.setMember(member1);
        friend1.setFriend(member2);
        friend1.setAreWeFriend(true);

        friendRepository.save(friend1);

        Friend friend2 = new Friend();
        friend2.setMember(member2);
        friend2.setFriend(member1);
        friend2.setAreWeFriend(false);

        friendRepository.save(friend2);

    }

    public void acceptAddFriend(Long id1, Long id2) { // id1 -> id2 친구 추가 수락
        Member member1 = memberRepository.findById(id1).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));
        Member member2 = memberRepository.findById(id2).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));

        Optional<Friend> byMemberId = friendRepository.findByMemberAndFriend(member1, member2);
        if (byMemberId.isPresent()){
            Friend friend = byMemberId.get();
            friend.setAreWeFriend(true);
        }
    }

    public void denyAddFriend(Long id1, Long id2) { // id1 -> id2 친구 추가 거절
        Member member1 = memberRepository.findById(id1).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));
        Member member2 = memberRepository.findById(id2).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));

        Optional<Friend> byMemberId1 = friendRepository.findByMemberAndFriend(member1, member2);
        Optional<Friend> byMemberId2 = friendRepository.findByMemberAndFriend(member2, member1);

        if (byMemberId1.isPresent() && byMemberId2.isPresent()){
            Friend friend1 = byMemberId1.get();
            Friend friend2 = byMemberId2.get();
            friendRepository.delete(friend1);
            friendRepository.delete(friend2);
        }
    }

    public boolean areWeFriend(Long id1, Long id2) {
        Member member1 = memberRepository.findById(id1).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));
        Member member2 = memberRepository.findById(id2).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));

        Optional<Friend> byMemberId1 = friendRepository.findByMemberAndFriend(member1, member2);
        Optional<Friend> byMemberId2 = friendRepository.findByMemberAndFriend(member2, member1);

        if (byMemberId1.isPresent() && byMemberId2.isPresent()){
            Friend friend1 = byMemberId1.get();
            Friend friend2 = byMemberId2.get();

            if (friend1.isAreWeFriend() && friend2.isAreWeFriend()){
                return true;
            }
            else {
                throw new IllegalStateException("친구 요청 중입니다");
            }
        }
        else {
            throw new IllegalStateException("친구가 아닙니다");
        }

    }

}
