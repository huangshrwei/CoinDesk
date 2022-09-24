package com.coinmill.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.coinmill.dto.CoindeskDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CoindeskUtil {

    public static JsonNode get(String urlString) throws Exception {
    	URL url = new URL(urlString);    	
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(url);
    }	
	
    public static String stream(String urlString) throws IOException {    	
    	URL url = new URL(urlString); 
        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        }
    }    
    
    // 使用 ObjectMapper 將 json 轉成泛型 class
    public static <T> CoindeskDto<T> convert(String json, Class<T> targetClass) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        CoindeskDto<T> hit = null;

        JavaType classType = objectMapper.getTypeFactory().constructParametricType(CoindeskDto.class, targetClass);
        hit = objectMapper.readValue(json, classType);

        return hit;
    }	    
    
    
}
