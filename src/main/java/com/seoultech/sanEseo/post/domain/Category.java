package com.seoultech.sanEseo.post.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    DODREAM(0), CUSTOM(1);

    private final int value;

    Category(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    public static Category from(int value) {
        for (Category category : values()) {
            if (category.getValue() == value) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown category value: " + value);
    }
}