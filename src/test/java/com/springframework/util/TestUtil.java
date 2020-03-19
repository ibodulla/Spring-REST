package com.springframework.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * Ibodulla Ibodullaev created on 3/18/2020 inside the package - com.springframework.util
 */
public final class TestUtil {

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    public static <T> T jsonToObject(String json, Class<T> classOf) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOf);
    }
}