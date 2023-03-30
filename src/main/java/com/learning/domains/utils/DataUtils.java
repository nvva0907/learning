package com.learning.domains.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class DataUtils {

    public static String convertObjectToJsonString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public static Set<Currency> getAllCurrencies() {
        Set<Currency> currencies = new HashSet<>();
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            try {
                Currency currency = Currency.getInstance(locale);
                if (currency != null) {
                    currencies.add(currency);
                }
            } catch (Exception exc) {
                // Locale not found
            }
        }
        return currencies;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static <T> List<T> getListValueFromMap(Map<?, T> map) {
        return new ArrayList<>(map.values());
    }
}
