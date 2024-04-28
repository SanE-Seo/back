package com.seoultech.sanEseo.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("postId") Long postId) {
        PostImage postImage = imageService.uploadImageToS3(postId, file);
        return ResponseEntity.ok(postImage);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostImages(@PathVariable Long postId) {

        return ResponseEntity.ok(imageService.getPostImages(postId));
    }
}
