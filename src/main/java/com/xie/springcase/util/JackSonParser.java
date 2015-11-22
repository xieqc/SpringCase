package com.xie.springcase.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JackSonParser {
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(df);
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String beanToJson(T t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Object jsonToBean(String content, Class<T> t) {
        try {
            return objectMapper.readValue(content, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
