package com.seoultech.sanEseo.member.adapter.in.web.dto;

import com.seoultech.sanEseo.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    private Long memberId;
    private String name;
    private String email;
    private String profile;

    public static MemberResponse fromEntity(Member member){
        return MemberResponse.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .profile(member.getProfile())
                .build();
    }
}
