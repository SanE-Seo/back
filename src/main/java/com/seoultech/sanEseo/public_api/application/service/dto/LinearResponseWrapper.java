package com.seoultech.sanEseo.public_api.application.service.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LinearResponseWrapper {
    @JsonAlias({"SdeDoDreamWay05LW", "SdeDoDreamWay02LW", "SdeDoDreamWay04LW"})
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

    public static class Way {
        @JsonProperty("OBJECTID")
        private String objectId;
        @JsonProperty("NAME")
        private String name;
        @JsonProperty("LAT")
        private String lat;
        @JsonProperty("LNG")
        private String lng;

        // Getters
        public String getObjectId() {
            return objectId;
        }

        public String getName() {
            return name;
        }


        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }

        @Override
        public String toString() {
            return "{" +
                    "objectId='" + objectId + '\'' +
                    ", name='" + name + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lng='" + lng + '\'' +
                    '}';
        }
    }
}
