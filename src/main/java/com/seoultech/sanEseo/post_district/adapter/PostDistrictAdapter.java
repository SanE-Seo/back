package com.seoultech.sanEseo.post_district.adapter;


import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post_district.application.port.PostDistrictPort;
import com.seoultech.sanEseo.post_district.domain.PostDistrict;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostDistrictAdapter implements PostDistrictPort {

    private final PostDistrictRepository postDistrictRepository;

    public PostDistrictAdapter(PostDistrictRepository postDistrictRepository) {
        this.postDistrictRepository = postDistrictRepository;
    }


    @Override
    public List<PostDistrict> findByDistrictId(Long districtId) {
        return postDistrictRepository.findByDistrictId(districtId);
    }

    @Override
    public void save(PostDistrict postDistrict) {
        postDistrictRepository.save(postDistrict);
    }

    @Override
    public List<PostDistrict> findByPostId(Long postId) {
        return postDistrictRepository.findByPostId(postId);
    }

    @Override
    public void deleteAll(List<PostDistrict> existingRelations) {
        postDistrictRepository.deleteAll(existingRelations);
    }

    @Override
    public List<PostDistrict> findAll() {
        return postDistrictRepository.findAll();
    }

    @Override
    public List<PostDistrict> findByPostCategory(Category category) {
        return postDistrictRepository.findByPostCategory(category);
    }

    @Override
    public Slice<PostDistrict> findByPostCategory(Category category, Pageable pageable) {
        return postDistrictRepository.findByPostCategory(category, pageable);
    }

}
