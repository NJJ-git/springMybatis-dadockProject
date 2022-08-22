package com.acorn.dadockProject.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.acorn.dadockProject.dto.Book;
import com.acorn.dadockProject.dto.Comment;
import com.acorn.dadockProject.dto.Library;
import com.acorn.dadockProject.dto.Paging;
import com.acorn.dadockProject.dto.ReadBook;
import com.acorn.dadockProject.dto.User;
import com.acorn.dadockProject.mapper.CommentMapper;
import com.acorn.dadockProject.mapper.LibraryMapper;
import com.acorn.dadockProject.mapper.ReadBookMapper;
import com.acorn.dadockProject.service.BookApiCallService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private ReadBookMapper readBookMapper;
	@Autowired
	private LibraryMapper libraryMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	BookApiCallService bookApiCallService;
	@Autowired
	ObjectMapper objectMapper;
	

	
	@GetMapping("/register")		
	public void register() {
		
	}
	
	@GetMapping("/detail/{isbn}") //isbn 검색// 
	public String detail(@PathVariable String isbn, Book book, Comment comment,
			Model model) throws Exception {
		
		JSONArray naver_result_arr=new JSONArray();
		JSONObject naver_result = bookApiCallService.get("https://openapi.naver.com/"
				+ "v1/search/book.json?query='"+isbn+"'");
		naver_result_arr=(JSONArray) naver_result.get("items");
		
		// JSONArray 파싱
		String jsonBookArray=naver_result.get("items").toString();
		int star=0;
		List<Book> bookDetails=objectMapper.readValue(jsonBookArray, new TypeReference<List<Book>>(){});
		Book bookDetail=bookDetails.get(0);
		
		// Comment List 가져옴
		List<Comment> comments=commentMapper.selectByIsbnAll(isbn);
		
		model.addAttribute("comments",comments);
		model.addAttribute("bookDetails", bookDetails);
		return "/book/detail";
	}
	
	@CrossOrigin
	@GetMapping("/recommend")
	public String list(Model model ) {
//		List<ReadBook> recommendList=readBookMapper.selectAll();
		List<ReadBook> recommendList=readBookMapper.selectByUserRecommendAll(); //db
		List<ReadBook> applicationList=readBookMapper.selectByAppRecommendAll(); //naver or db

		model.addAttribute("recommendList",recommendList);
		model.addAttribute("applicationList",applicationList);
		System.out.println(recommendList);
		return "/book/recommend";
	}
	
	@GetMapping("/search")
	public void search() {
		
	}
	
	@GetMapping("/searchList/{page}") //키워ㅜ드 검색 페이징 섞여있음
	public String searchList(@PathVariable int page, @RequestParam String text, Model model) throws Exception {
		JSONArray naver_result_arr=new JSONArray();

		int display=30;
		int start=((page-1)*display)+1;
		int row=display;
		 
		String query=URLEncoder.encode(text, "UTF-8");
		
		JSONObject naver_result = bookApiCallService.get("https://openapi.naver.com/"
				+ "v1/search/book.json?start="+start+"&display="+display+"&query="+query);
		
		naver_result_arr=(JSONArray) naver_result.get("items");
		
		// JSONArray 파싱
		String jsonBookArray=naver_result.get("items").toString();
		
		List<Book> bookList=objectMapper.readValue(jsonBookArray, new TypeReference<List<Book>>(){});
		
		Long total_long=(Long)naver_result.get("total");
		int total=Math.toIntExact(total_long);
		int rowCount=total;
		Paging paging = new Paging(page, rowCount, "/book/searchList/", row); //질문
		
		model.addAttribute("paging", paging);
		model.addAttribute("rowCount", rowCount);
		model.addAttribute("page",page);
		model.addAttribute("text", text);
		model.addAttribute("total", total);
		model.addAttribute("bookList", bookList);
		
		return "/book/searchList";
		
	}
	
	@GetMapping("/insert.do")
	public void insert(@SessionAttribute(name="loginUser", required=false) User loginUser, Model model, String isbn) {
		int star=0;
		if (loginUser!=null) {
			Library library=libraryMapper.selectUserIdAndIsbn(isbn, loginUser.getUser_id());
			star=library.getStar();
		}
		model.addAttribute("star", star);
	}
	@PostMapping("/insert.do")
	public String insert(ReadBook readBook ,Library library, Comment comment, String user_id,
		HttpSession session) {
		int insertReadBook=0;
		int insertLibrary=0;
		int insertComment=0;
		
		// 세션에 존재하는 user_id 받아 입력
		Object loginUser_obj = session.getAttribute("loginUser");
		user_id=((User) loginUser_obj).getUser_id();
		library.setUser_id(user_id);
		comment.setUser_id(user_id);
		
		insertReadBook=readBookMapper.insertOne(readBook);
		try {
			TimeUnit.SECONDS.sleep(1);
			insertLibrary=libraryMapper.insertOne(library);
			insertComment=commentMapper.insertOne(comment);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(insertComment);
		System.out.println(insertLibrary);
		System.out.println(insertReadBook);
		session.getAttribute("loginUser");
		if(insertLibrary>0&&insertComment>0) {
			return "redirect:/library/list/1";
		}else {
			return "redirect:/library/list/1";
		}
	}
}
