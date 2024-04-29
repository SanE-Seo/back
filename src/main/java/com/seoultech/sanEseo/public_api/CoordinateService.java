package com.seoultech.sanEseo.public_api;

import com.seoultech.sanEseo.post.domain.Post;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordinateService {

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Transactional
    public void saveCoordinate(GetLinearResponse linearResponse, GetGeometryResponse geometryResponse, Post post) {
        Coordinate coordinate = new Coordinate(
                geometryResponse.getName(),
                linearResponse.getLat(),  // 예시로 첫번째 좌표의 위도를 저장
                linearResponse.getLng(),  // 예시로 첫번째 좌표의 경도를 저장
                geometryResponse.getType(),
                geometryResponse.getCoordinates(),
                post);
        coordinateRepository.save(coordinate);
    }

    public GetCoordinateResponse getCoordinateResponse(Post post) {
        Coordinate byPost = coordinateRepository.findByPost(post);
        return new GetCoordinateResponse(byPost.getLat(), byPost.getLng(), byPost.getType(), byPost.getCoordinates());
    }
}