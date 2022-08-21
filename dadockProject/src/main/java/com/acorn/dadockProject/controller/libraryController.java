package com.acorn.dadockProject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.acorn.dadockProject.dto.Book;
import com.acorn.dadockProject.dto.Library;
import com.acorn.dadockProject.mapper.LibraryMapper;
import com.acorn.dadockProject.service.BookApiCallService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	BookApiCallService bookApiCallService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private LibraryMapper libraryMapper;
	
	@GetMapping("/list/{page}")
	public String list(@PathVariable int page, Model model) {
		
		List<Library> libraryList = libraryMapper.selectAll();
		System.out.println(libraryList);
		model.addAttribute("libraryList", libraryList);
		return "/library/list";
	}
	
	@GetMapping("/detail")
	public void detail() {}
	
	@GetMapping("/insert/{isbn}")
	public String insert(@PathVariable String isbn, Book book,
			Model model) throws Exception {
		JSONArray naver_result_arr=new JSONArray();

		JSONObject naver_result = bookApiCallService.get("https://openapi.naver.com/"
				+ "v1/search/book.json?query='"+isbn+"'");
		
		naver_result_arr=(JSONArray) naver_result.get("items");
		
		// JSONArray 파싱
		String jsonBookArray=naver_result.get("items").toString();
		
		List<Book> bookDetails=objectMapper.readValue(jsonBookArray, new TypeReference<List<Book>>(){});
		Book bookDetail=bookDetails.get(0);
		model.addAttribute("bookDetails", bookDetails);
		System.out.println(bookDetail);
		
		return "/library/insert";
	}
	
	@PostMapping("/insert.do")
	public String insert(Library library,
		HttpSession session) {
		int insert=0;
		String userId;
		insert=libraryMapper.insertOne(library);
		session.getAttribute("loginUser");
		if(insert>0) {
			return "redirect:/library/list/1";
		}else {
			return "redirect:/library/list/1";
		}
	}
	
	@GetMapping("/update/{page}")
	public void update() {}
	
	@GetMapping("/delete")
	public void delete() {}
	
	
}
