package com.example.MyBlog.Service;

import com.example.MyBlog.Domain.Friend;
import com.example.MyBlog.Domain.Member;
import com.example.MyBlog.Dto.MemberCreateDto;
import com.example.MyBlog.Repository.FriendRepository;
import com.example.MyBlog.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    public void createMember(MemberCreateDto dto){ // 회원가입 24.1.1 엔티티를 받는것을 Dto로 변환하였다.

        Member member = Member.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password((dto.getPassword()))
                .build();

        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> byEmail = memberRepository.findByEmail(member.getEmail());

        if (byEmail.isPresent()){
            throw new IllegalStateException("이메일이 이미 존재합니다");
        }

        boolean byName = memberRepository.existsByName(member.getName()); //이름이 이미 존재 한다면

        if (byName){
            int topNameTag = memberRepository.findTopNameTagByName(member.getName());
            member.setNameTag(topNameTag + 1);
        }
        else {
            member.setNameTag(1);
        }

    }

    public void requestAddFriend(String nameAndTag1, String nameAndTag2) { // 친구 추가 신청

        Member member1 = splitNameAndTag(nameAndTag1);
        Member member2 = splitNameAndTag(nameAndTag2);

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

    public void acceptAddFriend(String nameAndTag1, String nameAndTag2) {// 친구 추가 수락

        Member member1 = splitNameAndTag(nameAndTag1);
        Member member2 = splitNameAndTag(nameAndTag2);

        Optional<Friend> byMemberId = friendRepository.findByMemberAndFriend(member1, member2);
        if (byMemberId.isPresent()){
            Friend friend = byMemberId.get();
            friend.setAreWeFriend(true);
        }
    }

    public void denyAddFriend(String nameAndTag1, String nameAndTag2) { // 친구 추가 거절
        Member member1 = splitNameAndTag(nameAndTag1);
        Member member2 = splitNameAndTag(nameAndTag2);

        Optional<Friend> byMemberId1 = friendRepository.findByMemberAndFriend(member1, member2);
        Optional<Friend> byMemberId2 = friendRepository.findByMemberAndFriend(member2, member1);

        if (byMemberId1.isPresent() && byMemberId2.isPresent()){
            Friend friend1 = byMemberId1.get();
            Friend friend2 = byMemberId2.get();
            friendRepository.delete(friend1);
            friendRepository.delete(friend2);
        }
    }

    public boolean areWeFriend(String nameAndTag1, String nameAndTag2) { // 친구 확인 메서드
        Member member1 = splitNameAndTag(nameAndTag1);
        Member member2 = splitNameAndTag(nameAndTag2);

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

    public Member splitNameAndTag(String nameAndTag) { //이름과 태그를 분리해주는 로직

        String[] part = nameAndTag.split("#");

        if (part.length == 2) {
            String name1 = part[0];
            int tag1 = Integer.parseInt(part[1]);
            Optional<Member> memberWithNT = memberRepository.findMemberWithNT(name1, tag1);

            if (memberWithNT.isPresent()) {
                return memberWithNT.get();
            } else {
                throw new IllegalStateException("잘못되었습니다.(이름 또는 태그 번호를 확인해주세요.)");
            }
        } else {
            throw new IllegalStateException("잘못된 입력입니다.");
        }
    }


}
