package com.acorn.dadockProject.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acorn.dadockProject.dto.BookApiCallSvc;

@Controller
public class BookApiCallSvcController {
	@RequestMapping(value = "/search")
    public ResponseEntity search(String str) {
        BookApiCallSvc bookApiCallService = new BookApiCallSvc();
        JSONObject result = new JSONObject();
        try {
            result = bookApiCallService.get("https://openapi.naver.com/v1/search/book.json?query=%EC%9E%90%EA%B8%B0%EA%B3%84%EB%B0%9C%2B%ED%8C%8C%EC%9D%B4%EC%8D%AC&display=10&start=1");
            JSONArray result_arr=(JSONArray)result.get("items");
            
            for(int i=0; i<result_arr.size()-1; i++) {
            	JSONObject reult_obj = (JSONObject)result_arr.get(i);
            	String img = (String)reult_obj.get("image");
            	String title = (String)reult_obj.get("title");
            	String description = (String)reult_obj.get("description");
            	String publisher = (String)reult_obj.get("publisher");
            	String isbn = (String)reult_obj.get("isbn");
            	String author = (String)reult_obj.get("author");
            	System.out.println(img+"||"+title+"||"+description+"||"+publisher+"||"+isbn+"||"+author);
            }
        }catch (Exception e) {
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(result, HttpStatus.OK);
	}
}
