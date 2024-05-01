package com.seoultech.sanEseo.post.application.service;

import com.seoultech.sanEseo.district.domain.District;
import com.seoultech.sanEseo.district.application.port.DistrictPort;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.post.exception.AuthorMismatchException;
import com.seoultech.sanEseo.post_district.domain.PostDistrict;
import com.seoultech.sanEseo.post_district.application.port.PostDistrictPort;
import com.seoultech.sanEseo.public_api.Coordinate;
import com.seoultech.sanEseo.public_api.CoordinateService;
import com.seoultech.sanEseo.public_api.GetCoordinateResponse;
import com.seoultech.sanEseo.public_api.GetGeometryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class PostService {
    private final PostPort postPort;
    private final MemberPort memberPort;
    private final DistrictPort districtPort;
    private final PostDistrictPort postDistrictPort;
    private final CoordinateService coordinateService;

    @Transactional
    public Post addPost(Long memberId, AddPostRequest request) {

        Member member = memberPort.loadById(memberId);

        // 중복 검사
        if (postPort.existsByNameAndDescription(request.getTitle(), request.getDescription())) {
            System.out.println("Post already exists with the same name and description");
            return null;
            // 이 부분에서 예외를 던지거나, null을 반환하거나, 다른 적절한 처리를 할 수 있습니다.
        }
        // 중복이 아닌 경우, Post 생성 및 저장
        Post post = new Post(
                member,
                request.getCategory(), request.getTitle(), request.getSubTitle(), request.getDescription(),
                request.getLevel(), request.getTime(), request.getDistance(), request.getCourseDetail(),
                request.getTransportation());

        GetGeometryResponse geometry = request.getGeometry();
        coordinateService.saveCoordinate(geometry, post);

        postPort.save(post);  // Post 저장

        // 관련 District와 PostDistrict 관계 설정
        District district = districtPort.findById(request.getDistrictId());
        if (district == null) {
            throw new IllegalArgumentException("District with ID " + request.getDistrictId() + " not found");
        }
        PostDistrict postDistrict = new PostDistrict(post, district);
        postDistrictPort.save(postDistrict);  // PostDistrict 저장

        return post;  // 저장된 Post 반환
    }

    public GetPostResponse getPost(Long postId) {
        Post post = postPort.getPost(postId);
        Member postMember = memberPort.loadById(post.getMember().getId());

        List<PostDistrict> postDistrictList = postDistrictPort.findByPostId(postId);
        String postDistrictName = postDistrictList.get(0).getDistrict().getName();

        GetCoordinateResponse geometry = coordinateService.getCoordinateResponse(post);

        return new GetPostResponse(
                post.getId(),
                postMember.getId(),
                postMember.getName(),
                postMember.getProfile(),
                post.getCategory(),
                post.getTitle(),
                post.getSubTitle(),
                post.getDescription(),
                post.getLevel(),
                post.getTime(),
                post.getDistance(),
                post.getCourseDetail(),
                post.getTransportation(), postDistrictName
                , geometry
        );
    }

    @Transactional
    public void updatePost(Long memberId, Long postId, UpdatePostRequest request) {

        Post post = postPort.getPost(postId);

        if(!memberId.equals(post.getMember().getId())) {
            throw new AuthorMismatchException("작성자만 수정할 수 있습니다.");
        }

        // 좌표 정보 업데이트
        Coordinate coordinate = coordinateService.findCoordinate(post);
        coordinate.update(request.getGeometry().getName(), request.getGeometry().getType(), request.getGeometry());

        // 게시글 정보 업데이트
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
    public void deletePost(Long memberId, Long postId) {

        Post post = postPort.getPost(postId);
        if(!memberId.equals(post.getMember().getId())) {
            throw new AuthorMismatchException("작성자만 삭제할 수 있습니다.");
        }

        // 관련된 PostDistrict 관계 삭제
        List<PostDistrict> relations = postDistrictPort.findByPostId(postId);
        postDistrictPort.deleteAll(relations);

        postPort.deletePost(postId);
    }


}
