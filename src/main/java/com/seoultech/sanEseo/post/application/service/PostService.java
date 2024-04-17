package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.district.District;
import com.seoultech.sanEseo.district.DistrictPort;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.post_district.PostDistrict;
import com.seoultech.sanEseo.post_district.PostDistrictPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PostService {
    private final PostPort postPort;
    private final DistrictPort districtPort;
    private final PostDistrictPort postDistrictPort;

    public PostService(PostPort postPort, DistrictPort districtPort, PostDistrictPort postDistrictPort) {
        this.postPort = postPort;
        this.districtPort = districtPort;
        this.postDistrictPort = postDistrictPort;
    }

    public void addPost(AddPostRequest request) {
        Post post = new Post(
                request.category(), request.title(), request.subTitle(),
                request.description(), request.level(), request.time(),
                request.coordinate(), request.images());
        postPort.save(post);

        // 중간테이블에 저장(PostDistrict)
        District district = districtPort.findById(request.districtId());
        PostDistrict postDistrict = new PostDistrict(post, district);
        postDistrictPort.save(postDistrict);

    }

    public GetPostResponse getPost(Long postId) {
        Post post = postPort.getPost(postId);
        List<PostDistrict> postDistrictList = postDistrictPort.findByPostId(postId);
        String[] postDistrictName = postDistrictList.stream()
                .map(postDistrict -> postDistrict.getDistrict().getName())
                .toArray(String[]::new);

        return new GetPostResponse(
                post.getId(), post.getCategory(), post.getTitle(), post.getSubTitle(),
                post.getDescription(), post.getLevel(), post.getTime(),
                post.getCoordinate(), post.getImages(), postDistrictName
        );
    }

    @Transactional
    public void updatePost(Long postId, UpdatePostRequest request) {
        Post post = postPort.getPost(postId);
        post.update(
                request.category(), request.title(), request.subTitle(),
                request.description(), request.coordinate(), request.images()
        );
        postPort.save(post);

        List<PostDistrict> existingRelations = postDistrictPort.findByPostId(postId);
        postDistrictPort.deleteAll(existingRelations);

        // 새로운 District 관계 추가
        if (request.districtIds() != null && !request.districtIds().isEmpty()) {
            for (Long districtId : request.districtIds()) {
                District district = districtPort.findById(districtId);
                PostDistrict postDistrict = new PostDistrict(post, district);
                postDistrictPort.save(postDistrict);
            }
        }
    }

    @Transactional
    public void deletePost(Long postId) {
        // 관련된 PostDistrict 관계 삭제
        List<PostDistrict> relations = postDistrictPort.findByPostId(postId);
        postDistrictPort.deleteAll(relations);

        postPort.deletePost(postId);

    }
}
