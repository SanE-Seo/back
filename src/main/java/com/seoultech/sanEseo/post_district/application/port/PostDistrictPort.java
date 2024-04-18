package com.seoultech.sanEseo.post_district.application.port;

import com.seoultech.sanEseo.post_district.domain.PostDistrict;

import java.util.List;

public interface PostDistrictPort {


    List<PostDistrict> findByDistrictId(Long districtId);

    void save(PostDistrict postDistrict);

    List<PostDistrict> findByPostId(Long postId);

    void deleteAll(List<PostDistrict> existingRelations);
}
