package com.acorn.dadockProject.dto;

import java.net.URI;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BookApiCallSvc {
	public JSONObject get(String url) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "oHHQl3B9tdmJcsAmpzSZ");
        headers.add("X-Naver-Client-Secret", "g0qmX35VwT");

        ResponseEntity<String> result = restTemplate.exchange(new URI(url),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>(){});
        int code = result.getStatusCodeValue();
        String data = result.getBody();

        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(data);
        System.out.println("object : "+object);
        JSONObject jsonObject = (JSONObject)object;
        return jsonObject;
    }
}
