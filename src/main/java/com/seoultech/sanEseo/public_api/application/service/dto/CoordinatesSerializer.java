package com.seoultech.sanEseo.public_api.application.service.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;

public class CoordinatesSerializer extends JsonSerializer<List<List<Double>>> {
    @Override
    public void serialize(List<List<Double>> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (List<Double> coord : value) {
            gen.writeStartObject();
            gen.writeNumberField("lat", coord.get(0));
            gen.writeNumberField("lng", coord.get(1));
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }
}
