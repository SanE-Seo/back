package com.seoultech.sanEseo.public_api.application.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetLinearResponse {
    private String objectId;
    private String name;
    private String lat;
    private String lng;
}
