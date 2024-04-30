package com.seoultech.sanEseo.post_district.application.port;

import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post_district.domain.PostDistrict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PostDistrictPort {


    List<PostDistrict> findByDistrictId(Long districtId);

    void save(PostDistrict postDistrict);

    List<PostDistrict> findByPostId(Long postId);

    void deleteAll(List<PostDistrict> existingRelations);

    List<PostDistrict> findAll();

    List<PostDistrict> findByPostCategory(Category category);

//    Slice<PostDistrict> findByCategory(Category category, Pageable pageable);
}
