package com.seoultech.sanEseo;

import com.seoultech.sanEseo.post.application.service.GetPostResponse;
import com.seoultech.sanEseo.post.application.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void 게시글조회(){

        postService.addPost(PostSteps.게시글등록요청_생성());
        // 게시글 등록
        final Long postId = 1L;


        // 게시글 조회
        final GetPostResponse response = postService.getPost(postId);

        // 게시글 응답 검증
        assertThat(response).isNotNull();
    }


}
