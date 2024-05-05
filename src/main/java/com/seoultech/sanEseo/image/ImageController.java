package com.seoultech.sanEseo.image;

import com.seoultech.sanEseo.global.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("postId") Long postId) {
        for (MultipartFile file : files) {
            imageService.uploadImageToS3(postId, file);
        }
        return ApiResponse.ok("이미지 업로드 성공");
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);
        return  ApiResponse.ok("이미지 업로드 성공");
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostImages(@PathVariable Long postId) {
        List<PostImage> postImages = imageService.getPostImages(postId);
        return ApiResponse.ok("게시글 이미지 조회 성공", postImages);
    }
}
