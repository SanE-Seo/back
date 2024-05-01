package com.seoultech.sanEseo.weather.application.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class WeatherAPIResponse {
    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {
        private Header header;
        private Body body;
    }

    @Data
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Data
    public static class Body {
        private String dataType;
        private Items items;
        private int pageNo;
        private int numOfRows;
        private int totalCount;
    }

    @Data
    public static class Items {
        private List<Item> item;
    }

    @Data
    public static class Item {
        private String baseDate;
        private String baseTime;
        private String category;
        private String fcstDate;
        private String fcstTime;
        private String fcstValue;
        private int nx;
        private int ny;
    }

    public String getValueByCategoryAndDateTime(String category, String fcstDate, String fcstTime) {
        for (Item item : response.body.items.item) {
            if (item.getCategory().equals(category) && item.getFcstDate().equals(fcstDate) && item.getFcstTime().equals(fcstTime)) {
                return item.getFcstValue();
            }
        }
        return null;
    }

    public String getValueByCategoryAndDate(String category, String fcstDate) {
        for (Item item : response.body.items.item) {
            if (item.getCategory().equals(category) && item.getFcstDate().equals(fcstDate)) {
                return item.getFcstValue();
            }
        }
        return null;
    }
}
