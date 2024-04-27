package com.seoultech.sanEseo.global.common;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;
import com.seoultech.sanEseo.global.property.AwsS3Property;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Uploader {

  private final AmazonS3 amazonS3;
  private final AwsS3Property property;

  public String uploadFile(String directoryPath, MultipartFile multipartFile) {
    String bucketUrl = property.getBucketUrl();
    String fileName = createFileName(directoryPath, multipartFile.getOriginalFilename());
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentLength(multipartFile.getSize());
    objectMetadata.setContentType(multipartFile.getContentType());
    try {
      InputStream inputStream = multipartFile.getInputStream();
      amazonS3.putObject(
              new PutObjectRequest(property.getBucket(), fileName, inputStream, objectMetadata));
      return bucketUrl + fileName;
    } catch (Exception e) {
      throw new BusinessException(ErrorType.INTERNAL_ERROR, "이미지 업로드 실패");
    }
  }

  private String createFileName(String directoryPath, String fileName) {
    String convertFilename = Base64.getUrlEncoder().encodeToString(fileName.getBytes());
    return directoryPath + UUID.randomUUID().toString().concat(convertFilename);
  }
}