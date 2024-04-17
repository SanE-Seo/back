package com.seoultech.sanEseo;

import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.application.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PostServiceTest {

    @Test
    void 게시글삭제() {

        // given
        final PostPort postPort = Mockito.mock(PostPort.class);
        final PostService postService = new PostService(postPort, districtPort, postDistrictPort);
        final Long postId = 1L;

        // when
        final ResponseEntity<Void> response = postService.deletePost(postId);

        // then
        assertThat(response.getStatusCodeValue()).isEqualTo(204);
    }
}
