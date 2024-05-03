package com.seoultech.sanEseo.public_api.application.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CoordinateRequest {

    private String name;
    private String type;

    private List<List<Double>> coordinates;

}
