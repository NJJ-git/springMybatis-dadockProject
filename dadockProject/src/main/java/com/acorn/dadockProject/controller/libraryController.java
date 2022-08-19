package com.acorn.dadockProject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acorn.dadockProject.dto.Library;
import com.acorn.dadockProject.mapper.LibraryMapper;

@Controller
@RequestMapping("/library")
public class libraryController {
	
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
	
	@GetMapping("/insert.do")
	public void insert() {}
	
	@PostMapping("/insert.do")
	public String insert(Library library) {
		return null;
	}
	
	@GetMapping("/update/{page}")
	public void update() {}
	
	@GetMapping("/delete")
	public void delete() {}
	
	
}
