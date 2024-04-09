package com.seoultech.sanEseo.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String name;
    private String password;
    private String email;
    private String profile;

    @Builder
    public Member(String userId, String name, String password, String email, String profile) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.profile = profile;
    }

}
