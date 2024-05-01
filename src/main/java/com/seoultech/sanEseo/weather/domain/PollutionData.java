package com.seoultech.sanEseo.weather.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PollutionData {
    private Float microDust;
    private Float ultraMicroDust;
    private Float ozone;
}
