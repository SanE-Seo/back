package com.seoultech.sanEseo;

import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.application.service.GetPostResponse;
import com.seoultech.sanEseo.post.application.service.PostService;
import com.seoultech.sanEseo.post.application.service.UpdatePostRequest;
import com.seoultech.sanEseo.post.domain.Category;
import com.seoultech.sanEseo.post.domain.Coordinate;
import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.post.domain.PostImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static com.seoultech.sanEseo.PostSteps.게시글수정요청_생성;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PostServiceTest {


    @Autowired
    private PostService postService;

    @Test
    void 게시글수정() {
        postService.addPost(PostSteps.게시글등록요청_생성());
        final Long postId = 1L;
        final UpdatePostRequest request = 게시글수정요청_생성();

        postService.updatePost(postId, request);

        ResponseEntity<GetPostResponse> response = postService.getPost(postId);
        GetPostResponse postResponse = response.getBody();
        assertThat(postResponse.category()).isEqualTo(Category.CUSTOM);
        assertThat(postResponse.title()).isEqualTo("수정된 제목");


    }



}
