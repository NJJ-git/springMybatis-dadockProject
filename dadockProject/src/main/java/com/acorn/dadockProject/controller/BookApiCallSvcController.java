package com.acorn.dadockProject.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acorn.dadockProject.dto.BookApiCallSvc;

@Controller
public class BookApiCallSvcController {
	@RequestMapping("/search")
	
	@GetMapping("/list")
    public ResponseEntity search(String str, Model model) {
        BookApiCallSvc bookApiCallService = new BookApiCallSvc();
        JSONObject result = new JSONObject();
        String text="";
        try {
			text=URLEncoder.encode("생애", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        try {
        	
            result = bookApiCallService.get("https://openapi.naver.com/v1/search/book.json?d_categ=310&query="+text);
            JSONArray result_arr=(JSONArray)result.get("items");
            
            for(int i=0; i<result_arr.size()-1; i++) {
            	JSONObject reult_obj = (JSONObject)result_arr.get(i);
            	String img = (String)reult_obj.get("image");
            	String title = (String)reult_obj.get("title");
            	String description = (String)reult_obj.get("description");
            	String publisher = (String)reult_obj.get("publisher");
            	String isbn_str = (String)reult_obj.get("isbn");
            	String author = (String)reult_obj.get("author");
            	System.out.println(isbn_str);
            }
        }catch (Exception e) {
            return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(result, HttpStatus.OK);
	}
}
