package com.seoultech.sanEseo.public_api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public class CourseResponseWrapper {
    @JsonAlias({"walkDoseongInfo", "walkHangangInfo", "walkSaengtaeInfo"})
    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        @JsonProperty("row")
        private List<Way> rows;

        public List<Way> getRows() {
            return rows;
        }


    }


    @Getter
    public static class Way {
        @JsonProperty("RNUM")
        private String objectId;
        @JsonProperty("COURSE_NAME")
        private String title;
        @JsonProperty("CODE_NAME")
        private String subTitle;
        @JsonProperty("DISTANCE")
        private String distance;
        @JsonProperty("LEAD_TIME")
        private String time;
        @JsonProperty("COURSE_LEVEL")
        private String level;
        @JsonProperty("AREA_GU")
        private String district;
        @JsonProperty("TRAFFIC_INFO")
        private String transportation;
        @JsonProperty("CONTENT")
        private String courseDetail;

        @Override
        public String toString() {
            return "{" +
                    "objectId='" + objectId + '\'' +
                    ", title='" + title + '\'' +
                    ", subTitle='" + subTitle + '\'' +
                    ", distance='" + distance + '\'' +
                    ", time='" + time + '\'' +
                    ", level='" + level + '\'' +
                    ", district='" + district + '\'' +
                    ", transportation='" + transportation + '\'' +
                    ", courseDetail='" + courseDetail + '\'' +
                    '}';
        }
    }
}
