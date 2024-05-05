package com.seoultech.sanEseo.like.application.service;


import com.seoultech.sanEseo.image.GetImageResponse;
import com.seoultech.sanEseo.image.ImageService;
import com.seoultech.sanEseo.image.PostImage;
import com.seoultech.sanEseo.like.application.port.LikePort;
import com.seoultech.sanEseo.like.domain.Likes;
import com.seoultech.sanEseo.like.exception.DuplicateLikesException;
import com.seoultech.sanEseo.like.exception.MemberNotLikedException;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.post_district.application.service.GetPostDistrictResponse;
import com.seoultech.sanEseo.post_district.application.service.PostDistrictService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikePort likePort;
    private final MemberPort memberPort;
    private final PostPort postPort;
    private final ImageService imageService;
    private @Lazy PostDistrictService postDistrictService;

    @Autowired
    public void setPostDistrictService(@Lazy PostDistrictService postDistrictService) {
        this.postDistrictService = postDistrictService;
    }


    public void addLike(Long memberId, Long postId) {

        Member member = memberPort.loadById(memberId);
        Post post = postPort.getPost(postId);

        boolean isLiked = likePort.existsByPostAndMember(post, member);
        if(isLiked) {
            throw new DuplicateLikesException("이미 좋아요 한 게시글입니다.");
        }

        Likes likes = Likes.builder()
                .member(member)
                .post(post)
                .build();

        likePort.save(likes);
    }

    @Transactional
    public void deleteLike(Long postId, Long memberId) {

        Member member = memberPort.loadById(memberId);
        Post post = postPort.getPost(postId);

        boolean isLiked = likePort.existsByPostAndMember(post, member);
        if(!isLiked) {
            throw new MemberNotLikedException("좋아요 하지 않은 게시글입니다.");
        }

        likePort.deleteByPostAndMember(post, member);
    }

    public int getLikeCount(Long postId) {
        Post post = postPort.getPost(postId);
        return likePort.countByPost(post);
    }

    public boolean hasMemberLikedPost(Long memberId, Long postId) {
        Member member = memberPort.loadById(memberId);
        Post post = postPort.getPost(postId);
        return likePort.existsByPostAndMember(post, member);
    }

    public List<Post> findLikedPostsByMember(Long memberId) {
        List<Likes> likes = likePort.findByMemberId(memberId);
        return likes.stream()
                .map(like -> postPort.getPost(like.getPost().getId()))
                .collect(Collectors.toList());
    }

    public List<GetPostDistrictResponse> filterPostsByCategory(List<Post> posts, int category) {
        return posts.stream()
                .filter(post -> post.getCategory().getValue() == category)
                .map(post -> {
                    Member author = post.getMember();
                    List<PostImage> images = imageService.getPostImages(post.getId());
                    List<GetImageResponse> imageResponses = images.stream()
                            .map(image -> new GetImageResponse(image.getImageUrl()))
                            .collect(Collectors.toList());
                    int likes = getLikeCount(post.getId());

                    // Assuming a method that retrieves a string of district names for the post
                    String districts = postDistrictService.getDistrictsForPost(post.getId());



                    return new GetPostDistrictResponse(
                            post.getId(),
                            author.getId(),
                            author.getName(),
                            author.getProfile(),
                            imageResponses,
                            post.getTitle(),
                            post.getSubTitle(),
                            post.getTime(),
                            likes,
                            post.getDistance(),
                            post.getLevel(),
                            districts,
                            0.0,
                            0.0
                    );
                })
                .collect(Collectors.toList());
    }

}
