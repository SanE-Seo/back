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
    private String name;
    private String password;
    private String email;
    private String profile;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Builder
    public Member(Long id, String name, String password, String email, String profile, Provider provider) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.profile = profile;
        this.provider = provider;
    }

}
