package com.seoultech.sanEseo.member.adapter.in.web.dto;

import com.seoultech.sanEseo.member.application.port.in.command.UpdateMemberCommand;
import org.springframework.web.multipart.MultipartFile;


public record UpdateMemberRequest(String name, MultipartFile profile) {

    public UpdateMemberCommand toCommand(String email) {
        return UpdateMemberCommand.builder()
            .email(email)
            .name(name)
            .profile(profile)
            .build();
    }
}
