package com.seoultech.sanEseo.public_api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.List;

@Converter
public class CoordinatesConverter implements AttributeConverter<List<LatLng>, String> {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<LatLng> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (IOException e) {
            throw new RuntimeException("Conversion error", e);
        }
    }

    @Override
    public List<LatLng> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<LatLng>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Conversion error", e);
        }
    }
}