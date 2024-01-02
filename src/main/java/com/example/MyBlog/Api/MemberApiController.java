package com.example.MyBlog.Api;

import com.example.MyBlog.Domain.Member;
import com.example.MyBlog.Dto.MemberCreateDto;
import com.example.MyBlog.Service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/new")// 회원 등록
    public ResponseEntity<Result> createMember(@RequestBody MemberCreateDto memberDto){

        memberService.createMember(memberDto);

        Result result = new Result("Member Created Successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/request")
    public ResponseEntity<String> requestAddFriend(@RequestParam String name1, @RequestParam String name2) { //NameTag

        memberService.requestAddFriend(name1, name2);
        return ResponseEntity.ok("Friend request sent successfully");
    }

    @PostMapping("/accept")
    public ResponseEntity<String> acceptAddFriend(@RequestParam String name1, @RequestParam String name2) { //NameTag

        memberService.acceptAddFriend(name1, name2);
        return ResponseEntity.ok("Now " + name1 + " and " + name2 + " are friend!");
    }

    @PostMapping("/deny")
    public ResponseEntity<String> denyAddFriend(@RequestParam String name1, @RequestParam String name2) { //NameTag

        memberService.denyAddFriend(name1, name2);
        return ResponseEntity.ok("You denied " + name2 +"'s reqeust!");
    }

    @PostMapping("/validate")
    public ResponseEntity<String> areWeFriend(@RequestParam String name1, @RequestParam String name2) { //NameTag

        boolean areWeFriend = memberService.areWeFriend(name1, name2);

        if (areWeFriend) {return ResponseEntity.ok(name1 + " and " + name2 + " are friend");}
        else { return ResponseEntity.ok("Not Friend"); }
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
