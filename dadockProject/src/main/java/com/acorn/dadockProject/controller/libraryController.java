package com.acorn.dadockProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library")
public class libraryController {
	
	
	
	@GetMapping("/list")
	public void libraryList() {}
	
	@GetMapping("/detail")
	public void libraryDetail() {}
	
	@GetMapping("/insert.do")
	public void libraryInsert() {}
	
	@GetMapping("/update")
	public void libraryUpdate() {}
	
	@GetMapping("/delete")
	public void libraryDelete() {}
	
	
}
