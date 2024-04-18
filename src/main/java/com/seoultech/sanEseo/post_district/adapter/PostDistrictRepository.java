package com.seoultech.sanEseo.post_district.adapter;

import com.seoultech.sanEseo.post_district.domain.PostDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDistrictRepository extends JpaRepository<PostDistrict, Long> {
    List<PostDistrict> findByPostId(Long postId);

    List<PostDistrict> findByDistrictId(Long districtId);
}
