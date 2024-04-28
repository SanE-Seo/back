package com.seoultech.sanEseo.post_district.application.service;

import com.seoultech.sanEseo.district.domain.District;
import com.seoultech.sanEseo.district.application.port.DistrictPort;
import com.seoultech.sanEseo.image.ImageService;
import com.seoultech.sanEseo.image.PostImage;
import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.post_district.application.port.PostDistrictPort;
import com.seoultech.sanEseo.post_district.domain.PostDistrict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostDistrictService {

    private final DistrictPort districtPort;
    private final PostDistrictPort postDistrictPort;
    private final ImageService imageService;

    public PostDistrictService(DistrictPort districtPort, PostDistrictPort postDistrictPort, ImageService imageService) {
        this.districtPort = districtPort;
        this.postDistrictPort = postDistrictPort;
        this.imageService = imageService;
    }

    public void createPostDistrictRelation(Post post, Long districtId) {
        District district = districtPort.findById(districtId);
        PostDistrict postDistrict = new PostDistrict(post, district);

        postDistrictPort.save(postDistrict);

    }

    public List<GetPostDistrictResponse> getPostDistrict(Long districtId) {
        List<PostDistrict> postDistricts = postDistrictPort.findByDistrictId(districtId);
        List<GetPostDistrictResponse> responses = postDistricts.stream().map(postDistrict -> {
            Post post = postDistrict.getPost();
            List<PostImage> images = imageService.getPostImages(post.getId());
            return new GetPostDistrictResponse(
                    images,
                    post.getTitle(),
                    post.getSubTitle(),
                    post.getTime(),
                    String.valueOf(0),  // 가정: Post 엔티티에 좋아요 수를 반환하는 getLikes() 메소드가 있음
                    post.getTransportation(),
                    post.getLevel(),
                    postDistrict.getDistrict().getName()
            );
        }).collect(Collectors.toList());
        return responses;
    }

    public List<GetPostDistrictResponse> getAllPostDistrict(int category) {
        Category categoryEnum = Category.from(category);
        List<PostDistrict> postDistricts = postDistrictPort.findByPostCategory(categoryEnum);
        List<GetPostDistrictResponse> responses = postDistricts.stream().map(postDistrict -> {
            Post post = postDistrict.getPost();
            List<PostImage> images = imageService.getPostImages(post.getId());
            return new GetPostDistrictResponse(
                    images,
                    post.getTitle(),
                    post.getSubTitle(),
                    post.getTime(),
                    String.valueOf(0),  // 가정: Post 엔티티에 좋아요 수를 반환하는 getLikes() 메소드가 있음
                    post.getTransportation(),
                    post.getLevel(),
                    postDistrict.getDistrict().getName()
            );
        }).collect(Collectors.toList());
        return responses;
    }
}
