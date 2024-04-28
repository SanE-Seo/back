package com.seoultech.sanEseo.image;

import com.seoultech.sanEseo.global.common.S3Uploader;
import com.seoultech.sanEseo.post.application.port.PostPort;
import com.seoultech.sanEseo.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageService {

    private final S3Uploader s3Uploader;
    private final PostPort postPort;
    private final PostImageRepository postImageRepository;

    @Autowired
    public ImageService(S3Uploader s3Uploader, PostPort postPort, PostImageRepository postImageRepository) {
        this.s3Uploader = s3Uploader;
        this.postPort = postPort;
        this.postImageRepository = postImageRepository;
    }

    public PostImage uploadImageToS3(Long postId, MultipartFile file) {
        // 지정된 디렉토리 경로 설정
        String directoryPath = "images/"; // S3 내 저장될 폴더 경로

        // S3에 이미지 업로드
        String imageUrl = s3Uploader.uploadFile(directoryPath, file);

        // 데이터베이스에 이미지 정보 저장
        Post post = postPort.getPost(postId);

        PostImage postImage = new PostImage(imageUrl, post);
        postImageRepository.save(postImage); // PostImage 엔티티 저장

        return postImage;
    }

    public void deleteImage(Long imageId) {
        postImageRepository.deleteById(imageId);
    }

    public List<PostImage> getPostImages(Long postId) {
        return postImageRepository.findByPostId(postId);
    }
}