package com.learning.domains.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class JsonUtils {
    private static ObjectMapper mObjectMapper;

    /**
     * Creates an {@link ObjectMapper} for mapping json objects. Mapper can be configured here
     *
     * @return created {@link ObjectMapper}
     */
    private static ObjectMapper getMapper() {
        if (mObjectMapper == null) {
            mObjectMapper = new ObjectMapper();
        }
        return mObjectMapper;
    }

    /**
     * Maps json string to specified class
     *
     * @param json   string to parse
     * @param tClass class of object in which json will be parsed
     * @param <T>    generic parameter for tClass
     * @return mapped T class instance
     * @throws IOException
     */
    public static <T> T entity(String json, Class<T> tClass) throws IOException {
        return getMapper().readValue(json, tClass);
    }

    /**
     * Maps json string to {@link ArrayList} of specified class object instances
     *
     * @param json   string to parse
     * @param tClass class of object in which json will be parsed
     * @param <T>    generic parameter for tClass
     * @return mapped T class instance
     * @throws IOException
     */
    public static <T> ArrayList<T> arrayList(String json, Class<T> tClass) throws IOException {
        TypeFactory typeFactory = getMapper().getTypeFactory();
        JavaType type = typeFactory.constructCollectionType(ArrayList.class, tClass);
        return getMapper().readValue(json, type);
    }

    /**
     * Writes specified object as string
     *
     * @param object object to write
     * @return result json
     * @throws IOException
     */
    public static String toJson(Object object) throws IOException {
        return getMapper().writeValueAsString(object);
    }

    /**
     * Convert int[] to ArrayList<Integer></>
     *
     * @param ints
     * @return
     */
    public static ArrayList<Integer> intArrayList(int[] ints) {
        return IntStream.of(ints).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @param object
     * @return
     */
    public static Map<String, Object> objectToMap(Object object) {
        Map<String, Object> maps =
                getMapper().convertValue(object, new TypeReference<Map<String, Object>>() {
                });
        return maps;
    }

    public static LocalDateTime secondsToDateTime(long seconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneOffset.UTC);
    }
}
