package com.seoultech.sanEseo.post_district;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostDistrictAdapter implements PostDistrictPort {

    private final PostDistrictRepository postDistrictRepository;

    public PostDistrictAdapter(PostDistrictRepository postDistrictRepository) {
        this.postDistrictRepository = postDistrictRepository;
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
}
