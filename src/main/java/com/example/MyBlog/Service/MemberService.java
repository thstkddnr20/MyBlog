package com.example.MyBlog.Service;

import com.example.MyBlog.Domain.Friend;
import com.example.MyBlog.Domain.Member;
import com.example.MyBlog.Domain.MemberRole;
import com.example.MyBlog.Repository.FriendRepository;
import com.example.MyBlog.Repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        Friend friend2 = new Friend(); // member2의 요청도 만들었지만 arewefriend를 false로 설정하여 수락을 아직 안한 상태로 만듦
        friend2.setMember(member2);
        friend2.setFriend(member1);
        friend2.setAreWeFriend(false);

        friendRepository.save(friend2);
    }

    public void acceptAddFriends(Long id1, Long id2) { // id1 -> id2 친구 추가 수락
        Member member1 = memberRepository.findById(id1).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));
        Member member2 = memberRepository.findById(id2).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));

        Friend byMemberId = friendRepository.findByMemberId(member1.getId());
        byMemberId.setAreWeFriend(true);
    }
}
