package com.smilepasta.urchin.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.qiniu.android.utils.StringMap;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Author: huangxiaoming
 * Date: 2018/5/29
 * Desc:
 * Version: 1.0
 */
public final class JsonUtil {
    private JsonUtil() {
    }

    public static String encode(StringMap
                                        map) {
        return new Gson().toJson(map.map());
    }

    public static String encode(Object obj) {
        return new GsonBuilder().serializeNulls().create().toJson(obj);
    }

    public static <T> T decode(String json, Class<T> classOfT) {
        return new Gson().fromJson(json, classOfT);
    }

    public static <T> T decode(String json, Type type) {
        return new Gson().fromJson(json, type);
    }

    public static StringMap decode(String json) {
        // CHECKSTYLE:OFF
        Type t = new TypeToken<Map<String, Object>>() {
        }.getType();
        // CHECKSTYLE:ON
        Map<String, Object> x = new Gson().fromJson(json, t);
        return new StringMap(x);
    }
}
