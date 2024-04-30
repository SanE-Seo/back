package com.seoultech.sanEseo.public_api;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetCoordinateResponse {

    String type;
    List<LatLng> coordinates;
}
