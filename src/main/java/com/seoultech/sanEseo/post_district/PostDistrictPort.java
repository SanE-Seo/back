package com.seoultech.sanEseo.post_district;

import java.util.List;

public interface PostDistrictPort {


    void save(PostDistrict postDistrict);

    List<PostDistrict> findByPostId(Long postId);

    void deleteAll(List<PostDistrict> existingRelations);
}
