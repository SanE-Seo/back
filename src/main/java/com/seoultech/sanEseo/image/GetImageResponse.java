package com.seoultech.sanEseo.image;

public class GetImageResponse {
    private String imageUrl;

        public GetImageResponse(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }
}
