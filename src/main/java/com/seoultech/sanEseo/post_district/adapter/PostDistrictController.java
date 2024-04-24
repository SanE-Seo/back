package com.seoultech.sanEseo.post_district.adapter;


import com.seoultech.sanEseo.global.response.ApiResponse;
import com.seoultech.sanEseo.post_district.application.service.GetPostDistrictResponse;
import com.seoultech.sanEseo.post_district.application.port.PostDistrictPort;
import com.seoultech.sanEseo.post_district.application.service.PostDistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
