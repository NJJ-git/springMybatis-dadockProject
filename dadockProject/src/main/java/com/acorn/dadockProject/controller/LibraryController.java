package com.acorn.dadockProject.controller;

import java.util.List;

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
import com.acorn.dadockProject.dto.Paging;
import com.acorn.dadockProject.dto.ReadBook;
import com.acorn.dadockProject.dto.User;
import com.acorn.dadockProject.mapper.LibraryMapper;
import com.acorn.dadockProject.mapper.ReadBookMapper;
import com.acorn.dadockProject.service.BookApiCallService;
import com.acorn.dadockProject.service.LibraryService;
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
	
	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private ReadBookMapper readBookMapper;
	
	@GetMapping("/list/{page}")
	public String list(
			@SessionAttribute(name="loginUser", required=false) User loginUser,
			@PathVariable int page, 
			Model model) {
		List<ReadBook> readBookList = readBookMapper.selectByIdReadBookAndLibrary(loginUser.getUser_id());
		model.addAttribute("readBookList", readBookList);
		int row=7;
		int startRow=(page-1)*row;
		List<Library> libraryList=libraryMapper.selectPageAll(startRow,row);
		int rowCount=libraryMapper.selectPageAllCount();
		
		Paging paging=new Paging(page, rowCount, "/library/list/",row);
		model.addAttribute("paging",paging);
		model.addAttribute("libraryList",libraryList);
		
		model.addAttribute("row",row);
		model.addAttribute("rowCount",rowCount);
		model.addAttribute("page",page);
		System.out.println(libraryList);
		
		return "/library/list";
	}
	
	@GetMapping("/detail/{library_no}")
	public String detail(@SessionAttribute(name="loginUser", required=false) User loginUser,
			@PathVariable int library_no, Model model) {
		List<ReadBook> readBook = readBookMapper.selectOneByIdReadBookAndLibrary(loginUser.getUser_id(), library_no);
		model.addAttribute("readBook", readBook);
		return "/library/detail";
	}
	
	@PostMapping("/update.do")
	public String update(Library library) {
		int update = 0;
		update = libraryMapper.updateOne(library);
		if(update>0) {
			return "redirect:/library/list/1";
		}else {
			return "redirect:/library/detail/{libraryNo}";
		}
	}
	
	@GetMapping("/delete/{libraryNo}")
	public String delete(@PathVariable int libraryNo) {
		int delete = 0;
		try {
			delete = libraryService.removeLibrary(libraryNo);
		} catch (Exception e) {e.printStackTrace();}
		
		  if(delete>0) {
		  	return "redirect:/library/list/1"; 
		  }else { 
		  	return "redirect:/library/detail/{libraryNo}"; 
		  }
		 
	}
	
	
	@GetMapping("/insert/{isbn}")
	public String insert(
			@PathVariable String isbn, Book book,
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
		String user_id;
		insert=libraryMapper.insertOne(library);
		Object user_id_obj=session.getAttribute("loginUser");
		user_id=user_id_obj.toString();
		if(insert>0) {
			return "redirect:/library/list/1";
		}else {
			return "redirect:/library/list/1";
		}
	}
	
}
