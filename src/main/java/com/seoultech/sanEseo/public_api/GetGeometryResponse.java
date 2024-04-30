package com.seoultech.sanEseo.public_api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetGeometryResponse {
    private String type;
    private String name;
    private List<LatLng> coordinates;
}
