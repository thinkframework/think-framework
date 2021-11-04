package io.github.thinkframework.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.Map;

/**
 * json工具类
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public class JsonUtil {
    public static ObjectMapper objectMapper = createObjectMapper();

    /**
     *
     * @return
     */
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    /**
     *
     * @param obj
     * @return
     */
    public static byte[] convertObjectToJsonBytes(Object obj){
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param obj
     * @return
     */
    public static String convertObjectToJsonString(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param content
     * @param toValueType
     * @param <T>
     * @return
     */
    public static <T> T convertJsonStringToObject(String content, Class<T> toValueType){
        try {
            return objectMapper.readValue(content,toValueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @param content
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String content, TypeReference<T> valueType){
        try {
            return objectMapper.readValue(content,valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
