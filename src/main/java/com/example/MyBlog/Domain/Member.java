package com.example.MyBlog.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Friend> friendsList = new ArrayList<>();

    public Member(String name, String email, String password, MemberRole memberRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.memberRole = memberRole;
    }

    public Member() {
    }
}
