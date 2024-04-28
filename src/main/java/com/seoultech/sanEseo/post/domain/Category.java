package com.seoultech.sanEseo.post.domain;

public enum Category {
    DODREAM, CUSTOM;

    public static Category from(int category) {
        if (category == 1) {
            return DODREAM;
        } else if (category == 2) {
            return CUSTOM;
        } else {
            throw new IllegalArgumentException("Unknown category: " + category);
        }
    }


}
