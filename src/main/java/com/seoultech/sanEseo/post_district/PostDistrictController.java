package com.seoultech.sanEseo.post_district;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class PostDistrictController {

    private final PostDistrictPort postDistrictPort;
    private final PostDistrictService postDistrictService;

    public PostDistrictController(PostDistrictPort postDistrictPort, PostDistrictService postDistrictService) {
        this.postDistrictPort = postDistrictPort;
        this.postDistrictService = postDistrictService;
    }


    @GetMapping("/{districtId}/posts")
    public ResponseEntity<List<GetPostDistrictResponse>> getPostDistrict(@PathVariable Long districtId) {
        List<GetPostDistrictResponse> responses = postDistrictService.getPostDistrict(districtId);
        return ResponseEntity.ok(responses);
    }
}
