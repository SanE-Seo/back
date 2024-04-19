package com.seoultech.sanEseo.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ReviewServiceTest {

    private ReviewService reviewService;
    private ReviewPort reviewPort;
    private ReviewRepository reviewRepository;


    @BeforeEach
    void setUp() {
        reviewRepository = new ReviewRepository();
        reviewPort = Mockito.mock(ReviewPort.class);
        reviewService = new ReviewService(reviewPort);
    }

    @Test
    void 리뷰등록() {

        Long memberId = 1L;
        Long postId = 1L;
        String content = "리뷰 내용";
        LocalDateTime now = LocalDateTime.now();
        CreateReviewRequest request = new CreateReviewRequest(memberId, postId, content, now);

        reviewService.createReview(request);

    }

    public record CreateReviewRequest(
            Long memberId,
            Long postId,
            String content,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createDate
    ) {
        public CreateReviewRequest {
            Assert.notNull(memberId, "사용자 ID는 필수입니다.");
            Assert.notNull(postId, "게시글 ID는 필수입니다.");
            Assert.hasText(content, "리뷰 내용은 필수입니다.");
            Assert.notNull(createDate, "리뷰 작성일은 필수입니다.");
        }
    }

    private class ReviewService {
        private ReviewPort reviewPort;

        private ReviewService(final ReviewPort reviewPort){
            this.reviewPort = reviewPort;
        }

        public void createReview(CreateReviewRequest request) {
            reviewPort.createReview(request);
        }
    }

    private class ReviewAdapter implements ReviewPort {

        private final ReviewRepository reviewRepository;

        private ReviewAdapter(ReviewRepository reviewRepository) {
            this.reviewRepository = reviewRepository;
        }

        @Override
        public void createReview(CreateReviewRequest request) {
            Review review = new Review(request.memberId(), request.postId(), request.content(), request.createDate());

            reviewRepository.save(review);
        }
    }

    public interface ReviewPort{
        void createReview(CreateReviewRequest reivew);
    }

    private class ReviewRepository {

        private final Map<Long, Review> persistence = new HashMap<>();
        private Long sequence = 0L;

        public void save(Review review) {
            review.assignId(++sequence);
            persistence.put(review.getId(), review);
        }
    }

    private class Review {
        private Long id;
        private Long memberId;
        private Long postId;
        private String content;
        private LocalDateTime createDate;

        public Review(Long memberId, Long postId, String content, LocalDateTime createDate) {
            this.memberId = memberId;
            this.postId = postId;
            this.content = content;
            this.createDate = createDate;
        }

        public Long getId() {
            return id;
        }

        public void assignId(Long id) {
            this.id = id;
        }
    }
}

