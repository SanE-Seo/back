package com.seoultech.sanEseo.post_district.application.service;

import com.seoultech.sanEseo.district.application.service.DistrictService;
import com.seoultech.sanEseo.district.domain.District;
import com.seoultech.sanEseo.district.application.port.DistrictPort;
import com.seoultech.sanEseo.image.GetImageResponse;
import com.seoultech.sanEseo.image.ImageService;
import com.seoultech.sanEseo.image.PostImage;
import com.seoultech.sanEseo.like.application.service.LikeService;
import com.seoultech.sanEseo.member.application.service.MemberService;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.application.service.PostService;
import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.post_district.application.port.PostDistrictPort;
import com.seoultech.sanEseo.post_district.domain.PostDistrict;
import com.seoultech.sanEseo.public_api.application.service.CoordinateService;
import com.seoultech.sanEseo.public_api.application.service.dto.GetCoordinateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostDistrictService {

    private final PostService postService;
    private final PostDistrictPort postDistrictPort;
    private final ImageService imageService;
    private @Lazy LikeService likeService;
    private final CoordinateService coordinateService;

    @Autowired
    public void setLikeService(@Lazy LikeService likeService) {
        this.likeService = likeService;
    }


    public List<GetPostDistrictResponse> getPostDistrict(Long districtId) {
        List<PostDistrict> postDistricts = postDistrictPort.findByDistrictId(districtId);
        // 해당 post의 image를 가져오기 위해 imageService를 사용
        return getPostDistrictResponses(postDistricts);
    }

    public List<GetPostDistrictResponse> getAllPostDistrict(Pageable pageable, int category) {

        Category categoryEnum = Category.from(category);
        Slice<PostDistrict> postDistrictsSlice = postDistrictPort.findByPostCategory(categoryEnum, pageable);

        // Slice의 실제 내용을 리스트로 변환하고, 응답 DTO 생성
        List<PostDistrict> postDistricts = postDistrictsSlice.getContent();
        return getPostDistrictResponses(postDistricts);
    }

    public List<GetPostDistrictResponse> getPostByLikesSortedDesc(int category, String districtPrefix) {
        Category categoryEnum = Category.from(category);
        List<PostDistrict> postDistricts = postDistrictPort.findByPostCategory(categoryEnum);

        if(districtPrefix != null) {
            postDistricts = postDistricts.stream()
                    .filter(postDistrict -> postDistrict.getDistrict().getName().startsWith(districtPrefix))
                    .collect(Collectors.toList());
        }

        // 좋아요 수에 따라 정렬
        postDistricts.sort((p1, p2) -> Integer.compare(
                likeService.getLikeCount(p2.getPost().getId()),
                likeService.getLikeCount(p1.getPost().getId())
        ));

        // 상위 3개의 게시물만 선택
        List<PostDistrict> topThreePosts = postDistricts.subList(0, Math.min(3, postDistricts.size()));

        return getPostDistrictResponses(topThreePosts);
    }


    private List<GetPostDistrictResponse> getPostDistrictResponses(List<PostDistrict> postDistricts) {
        List<GetPostDistrictResponse> responses = postDistricts.stream().map(postDistrict -> {
            Post post = postDistrict.getPost();
            Member author = post.getMember();

            // 좌표값 찾기
            GetCoordinateResponse coordinateResponse = coordinateService.getCoordinateResponse(post);
            List<List<Double>> coordinates = coordinateResponse.getCoordinates();
            List<Double> initial_value =  coordinates.get(0);


            List<PostImage> images = imageService.getPostImages(post.getId());
            List<GetImageResponse> imageResponses = images.stream().map(image -> new GetImageResponse(image.getImageUrl())).collect(Collectors.toList());
            int likeCount = likeService.getLikeCount(post.getId());
            return new GetPostDistrictResponse(
                    post.getId(),
                    author.getId(),
                    author.getName(),
                    author.getProfile(),
                    imageResponses,
                    post.getTitle(),
                    post.getSubTitle(),
                    post.getTime(),
                    likeCount,  // 가정: Post 엔티티에 좋아요 수를 반환하는 getLikes() 메소드가 있음
                    post.getDistance(),
                    post.getLevel(),
                    postDistrict.getDistrict().getName(),
                    initial_value.get(0),
                    initial_value.get(1)
            );
        }).collect(Collectors.toList());
        return responses;
    }


    public String getDistrictsForPost(Long id) {
        List<PostDistrict> postDistricts = postDistrictPort.findByPostId(id);
        return postDistricts.stream()
                .map(postDistrict -> postDistrict.getDistrict().getName())
                .collect(Collectors.joining(", "));
    }
}
