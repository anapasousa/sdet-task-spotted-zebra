package com.sdet.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

    public static JsonObject parseJson(String json) {
        JsonElement jsonElement = JsonParser.parseString(json);
        if (jsonElement.isJsonObject()) {
            return jsonElement.getAsJsonObject();
        }
        return null;
    }

    public static String getStringValue(JsonObject jsonObject, String... memberNames) {
        if (jsonObject != null) {
            JsonObject currentObject = jsonObject;
            for (String memberName : memberNames) {
                if (currentObject.has(memberName)) {
                    JsonElement jsonElement = currentObject.get(memberName);
                    if (jsonElement.isJsonObject()) {
                        currentObject = jsonElement.getAsJsonObject();
                    } else if (jsonElement.isJsonPrimitive()) {
                        return jsonElement.getAsString();
                    }
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}
