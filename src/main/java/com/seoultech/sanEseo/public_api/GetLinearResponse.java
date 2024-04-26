package com.seoultech.sanEseo.public_api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.geojson.GeoJsonObject;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetLinearResponse {
    private String objectId;
    private String name;
    private GeoJsonObject shape;
    private String lat;
    private String lng;
}
