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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PostServiceTest {


    @Autowired
    private PostService postService;

    @Test
    void 게시글수정() {
        postService.addPost(PostSteps.게시글등록요청_생성());
        final Long postId = 1L;
        final UpdatePostRequest request = new UpdatePostRequest(Category.CUSTOM, "수정된 제목", "수정된 부제목", "수정된 내용", 3, "소요시간", new Coordinate("수정된 좌표", 37.1234, 127.1234, "(4,89,127.013600598,37.526797483,127.116737933,37.562810756,0,0,0,0,0,0.138038272495688,12,oracle.sql.BLOB@1800d8)"), Arrays.asList(new PostImage("수정된 이미지1_URL"), new PostImage("수정된 이미지2_URL")));

        postService.updatePost(postId, request);

        ResponseEntity<GetPostResponse> response = postService.getPost(postId);
        GetPostResponse postResponse = response.getBody();
        assertThat(postResponse.category()).isEqualTo(Category.CUSTOM);
        assertThat(postResponse.title()).isEqualTo("수정된 제목");


    }


}
