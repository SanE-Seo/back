package com.seoultech.sanEseo.public_api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoordinatesDeserializer extends JsonDeserializer<List<List<Double>>> {
    @Override
    public List<List<Double>> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        List<List<Double>> coordinates = new ArrayList<>();
        while (p.nextToken() != JsonToken.END_ARRAY) {
            JsonNode node = p.getCodec().readTree(p);
            double lat = node.get("lat").asDouble();
            double lng = node.get("lng").asDouble();
            List<Double> coordPair = new ArrayList<>();
            coordPair.add(lat);
            coordPair.add(lng);
            coordinates.add(coordPair);
        }
        return coordinates;
    }
}