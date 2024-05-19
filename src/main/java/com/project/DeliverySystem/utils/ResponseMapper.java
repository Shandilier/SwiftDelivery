package com.project.DeliverySystem.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseMapper {

    public static Map<String, Object> getResponse(String status, String message){
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        return response;
    }
}
