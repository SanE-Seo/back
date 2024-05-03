package com.seoultech.sanEseo.public_api.application.service;

import com.seoultech.sanEseo.post.domain.Post;
import com.seoultech.sanEseo.public_api.adapter.CoordinateRepository;
import com.seoultech.sanEseo.public_api.application.service.dto.CoordinateRequest;
import com.seoultech.sanEseo.public_api.application.service.dto.GetCoordinateResponse;
import com.seoultech.sanEseo.public_api.domain.Coordinate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordinateService {

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Transactional
    public void saveCoordinate(CoordinateRequest geometryResponse, Post post) {
        Coordinate coordinate = new Coordinate(
                geometryResponse.getName(),
                geometryResponse.getType(),
                geometryResponse.getCoordinates(),
                post);
        coordinateRepository.save(coordinate);
    }

    public GetCoordinateResponse getCoordinateResponse(Post post) {
        Coordinate byPost = coordinateRepository.findByPost(post);
        return new GetCoordinateResponse(byPost.getType(), byPost.getCoordinates());
    }

    // coordinate찾기
    public Coordinate findCoordinate(Post post) {
        return coordinateRepository.findByPost(post);
    }
}