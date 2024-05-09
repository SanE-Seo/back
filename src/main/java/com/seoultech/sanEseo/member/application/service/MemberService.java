package com.seoultech.sanEseo.member.application.service;

import com.seoultech.sanEseo.global.common.S3Uploader;
import com.seoultech.sanEseo.image.GetImageResponse;
import com.seoultech.sanEseo.image.ImageService;
import com.seoultech.sanEseo.image.PostImage;
import com.seoultech.sanEseo.like.application.service.LikeService;
import com.seoultech.sanEseo.member.adapter.in.web.dto.MemberResponse;
import com.seoultech.sanEseo.member.application.port.in.command.UpdateMemberCommand;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.member.exception.DuplicateEmailException;
import com.seoultech.sanEseo.member.exception.DuplicateNameException;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.post_district.application.port.PostDistrictPort;
import com.seoultech.sanEseo.post_district.application.service.GetPostDistrictResponse;
import com.seoultech.sanEseo.post_district.domain.PostDistrict;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberPort memberPort;
    private final S3Uploader s3Uploader;
    private final PostPort postPort;
    private final PostDistrictPort postDistrictPort;
    private final ImageService imageService;
    private final LikeService likeService;


    public MemberService(MemberPort memberPort, S3Uploader s3Uploader, PostPort postPort, PostDistrictPort postDistrictPort, ImageService imageService, LikeService likeService) {
        this.memberPort = memberPort;
        this.s3Uploader = s3Uploader;
        this.postPort = postPort;
        this.postDistrictPort = postDistrictPort;
        this.imageService = imageService;
        this.likeService = likeService;
    }

    public void addMember(Member member) {
        // DTO -> Entity
        memberPort.save(member);
    }

    public Member loadMember(Long id) {
        // Entity -> DTO
        return memberPort.loadById(id);
    }

    public Member loadMemberByEmail(String email) {
        return memberPort.loadByEmail(email);
    }


    public void checkDuplicateName(String name) {
        if(memberPort.existsByName(name)) {
            throw new DuplicateNameException("이미 존재하는 이름입니다.");
        }
    }

    public void checkDuplicateEmail(String email) {
        if(memberPort.existsByEmail(email)) {
            throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
        }
    }

    public String generateName() {
        return "서울시민#" + memberPort.getNewIndex();
    }

    @Transactional
    public MemberResponse updateMember(UpdateMemberCommand command) {
        Member member = memberPort.loadById(command.getMemberId());

        if(command.getName() != null && !command.getName().equals(member.getName())) {
            checkDuplicateName(command.getName());
            member.setName(command.getName());
        }

        if(command.getProfile() != null) {
            final String path = "app/profile/";
            String profile = s3Uploader.uploadFile(path, command.getProfile());
            member.setProfile(profile);
        }

        return MemberResponse.fromEntity(memberPort.save(member));
    }


}
