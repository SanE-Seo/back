package com.seoultech.sanEseo.public_api;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetCoordinateResponse {

    String type;

    @JsonSerialize(using = CoordinatesSerializer.class)
    List<List<Double>> coordinates;
}
