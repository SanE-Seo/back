package com.seoultech.sanEseo.post_district.adapter;


import com.seoultech.sanEseo.global.config.web.AuthMember;
import com.seoultech.sanEseo.global.config.web.LoginMember;
import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.member.application.service.MemberService;
import com.seoultech.sanEseo.post_district.application.service.GetPostDistrictResponse;
import com.seoultech.sanEseo.post_district.application.service.PostDistrictService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class PostDistrictController {

    private final PostDistrictService postDistrictService;
    private final MemberService memberService;

    public PostDistrictController(PostDistrictService postDistrictService, MemberService memberService) {
        this.postDistrictService = postDistrictService;
        this.memberService = memberService;
    }


    @GetMapping("/{districtId}/posts")
    public ResponseEntity<?> getPostDistrict(@PathVariable Long districtId) {
        List<GetPostDistrictResponse> responses = postDistrictService.getPostDistrict(districtId);
        return ApiResponse.ok("게시글 목록 조회 성공", responses);
    }

    //전체 게시글 조회
    @GetMapping("/posts")
    public ResponseEntity<?> getAllPostDistrictByCategory(@RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "12") int size,
                                                          @RequestParam(value = "category", defaultValue = "1") int category) {
        Pageable pageable = PageRequest.of(page, size);
        List<GetPostDistrictResponse> responses = postDistrictService.getAllPostDistrict(pageable, category);
        return ApiResponse.ok("전체 게시글 목록 조회 성공", responses);
    }

    //좋아요 순으로 정렬된 게시글 조회
    @GetMapping("/posts/{category}/sorted-by-likes")
    public ResponseEntity<?> getPostByLikesSortedDesc(@PathVariable int category) {
        List<GetPostDistrictResponse> responses = postDistrictService.getPostByLikesSortedDesc(category);
        return ApiResponse.ok("좋아요 순으로 정렬된 게시글 목록 조회 성공", responses);
    }


}
