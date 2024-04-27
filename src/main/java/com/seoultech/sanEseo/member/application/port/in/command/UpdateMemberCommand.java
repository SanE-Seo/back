package com.seoultech.sanEseo.member.application.port.in.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
@EqualsAndHashCode(callSuper = false)
public class UpdateMemberCommand{

    private final String email;
    private final String name;
    private final MultipartFile profile;

    @Builder
    public UpdateMemberCommand(String email, String name, MultipartFile profile) {
        this.email = email;
        this.name = name;
        this.profile = profile;
    }
}
