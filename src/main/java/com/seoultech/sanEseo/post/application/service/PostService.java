package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.district.domain.District;
import com.seoultech.sanEseo.district.application.port.DistrictPort;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.post_district.domain.PostDistrict;
import com.seoultech.sanEseo.post_district.application.port.PostDistrictPort;
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

    @Transactional
    public void addPost(AddPostRequest request) {
        Post post = new Post(
                request.getCategory(), request.getTitle(), request.getSubTitle(), request.getDescription(), request.getLevel(), request.getTime(),
                request.getDistance(), request.getCourseDetail(), request.getTransportation());
        postPort.save(post);

        // 여러 District와의 관계 설정
        District district = districtPort.findById(request.getDistrictId());
            PostDistrict postDistrict = new PostDistrict(post, district);
            postDistrictPort.save(postDistrict);  // PostDistrict 저장
    }

    public GetPostResponse getPost(Long postId) {
        Post post = postPort.getPost(postId);
        List<PostDistrict> postDistrictList = postDistrictPort.findByPostId(postId);
        String postDistrictName = postDistrictList.get(0).getDistrict().getName();

        return new GetPostResponse(
                post.getId(), post.getCategory(), post.getTitle(), post.getSubTitle(),
                post.getDescription(), post.getLevel(), post.getTime(),
                post.getDistance(),post.getCourseDetail(), post.getTransportation(), postDistrictName
        );
    }

    @Transactional
    public void updatePost(Long postId, UpdatePostRequest request) {
        Post post = postPort.getPost(postId);
        post.update(
                request.getCategory(), request.getTitle(), request.getSubTitle(),
                request.getDescription(), request.getLevel(), request.getTime(),
                request.getDistance(), request.getCourseDetail(), request.getTransportation()
        );
        postPort.save(post);

        List<PostDistrict> existingRelations = postDistrictPort.findByPostId(postId);
        postDistrictPort.deleteAll(existingRelations);

        // 새로운 District 관계 추가
            District district = districtPort.findById(request.getDistrictId());
                PostDistrict postDistrict = new PostDistrict(post, district);
                postDistrictPort.save(postDistrict);
    }

    @Transactional
    public void deletePost(Long postId) {
        // 관련된 PostDistrict 관계 삭제
        List<PostDistrict> relations = postDistrictPort.findByPostId(postId);
        postDistrictPort.deleteAll(relations);

        postPort.deletePost(postId);

    }
}
