package com.seoultech.sanEseo.post_district.adapter;


import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post_district.application.service.GetPostDistrictResponse;
import com.seoultech.sanEseo.post_district.application.port.PostDistrictPort;
import com.seoultech.sanEseo.post_district.application.service.PostDistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class PostDistrictController {

    private final PostDistrictService postDistrictService;

    public PostDistrictController(PostDistrictService postDistrictService) {
        this.postDistrictService = postDistrictService;
    }


    @GetMapping("/{districtId}/posts")
    public ResponseEntity<?> getPostDistrict(@PathVariable Long districtId) {
        List<GetPostDistrictResponse> responses = postDistrictService.getPostDistrict(districtId);
        return ApiResponse.ok("게시글 목록 조회 성공", responses);
    }

    //전체 게시글 조회
    @GetMapping("/posts/{category}")
    public ResponseEntity<?> getAllPostDistrictByCategory(@PathVariable int category) {
        List<GetPostDistrictResponse> responses = postDistrictService.getAllPostDistrict(category);
        return ApiResponse.ok("전체 게시글 목록 조회 성공", responses);
    }

    //좋아요 순으로 정렬된 게시글 조회
    @GetMapping("/posts/{category}/sorted-by-likes")
    public ResponseEntity<?> getPostByLikesSortedDesc(@PathVariable int category) {
        List<GetPostDistrictResponse> responses = postDistrictService.getPostByLikesSortedDesc(category);
        return ApiResponse.ok("좋아요 순으로 정렬된 게시글 목록 조회 성공", responses);
    }
}
