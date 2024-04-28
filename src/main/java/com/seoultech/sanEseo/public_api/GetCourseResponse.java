package com.seoultech.sanEseo.public_api;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCourseResponse {

    private String name;
    private String subTitle;
    private String distance;
    private String time;
    private String level;
    private String district;
    private String transportation;
    private String courseDetail;
    private String descrption;

}
