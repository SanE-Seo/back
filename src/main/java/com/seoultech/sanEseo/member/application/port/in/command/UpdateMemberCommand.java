package com.seoultech.sanEseo.member.application.port.in.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
@EqualsAndHashCode(callSuper = false)
public class UpdateMemberCommand{

    private final Long memberId;
    private final String name;
    private final MultipartFile profile;

    @Builder
    public UpdateMemberCommand(Long memberId, String name, MultipartFile profile) {
        this.memberId = memberId;
        this.name = name;
        this.profile = profile;
    }
}
