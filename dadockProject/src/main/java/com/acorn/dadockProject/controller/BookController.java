package com.acorn.dadockProject.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acorn.dadockProject.service.BookApiCallService;

@Controller
@RequestMapping("/book")
public class BookController {
	@GetMapping("/register")		
	public void register() {
		
	}
	
	@GetMapping("/detail")
	public void detail() {
		
	}
	
	@GetMapping("/recommend")
	public void recommend() {
		
	}
	
	@GetMapping("/search")
	public void search() {
		
	}
	
	@GetMapping("/searchList")
    public ResponseEntity searchList(@RequestParam String text, Model model) {
        BookApiCallService bookApiCallService = new BookApiCallService();
        JSONObject naver_result = new JSONObject();
        try {
        	String query=URLEncoder.encode(text, "UTF-8");
        	naver_result = bookApiCallService.get("https://openapi.naver.com/v1/search/book.json?"
            		+ "start=1&display=100&query="+query);
//        	result=bookApiCallService.get("https://openapi.naver.com/v1/search/book_adv.xml?d_catg=100&d_titl="+query);
            JSONArray naver_result_arr=(JSONArray)naver_result.get("items");
            Long total_long=(Long)naver_result.get("total"); // total : long 타입으로 들어옴
            int total=Math.toIntExact(total_long);
//            System.out.println(total);
        }catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity(naver_result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(naver_result, HttpStatus.OK);
	}
}