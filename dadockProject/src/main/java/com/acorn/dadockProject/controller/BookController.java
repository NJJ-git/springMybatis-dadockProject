package com.acorn.dadockProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@GetMapping("/list")
	public void list() {
		
	}
	
	@GetMapping("/detail")
	public void detail() {
		
	}
	
	@GetMapping("/recommend")
	public void recommend() {
		
	}
}
