package com.acorn.dadockProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.acorn.dadockProject.dto.ReadBook;
import com.acorn.dadockProject.mapper.ReadBookMapper;

@Controller
public class MainController {
	@Autowired
	ReadBookMapper readBookMapper;
	
	@GetMapping("")
	public String main(Model model) {
		List<ReadBook> recommendList=readBookMapper.selectByUserRecommendAll(); //db
		List<ReadBook> applicationList=readBookMapper.selectByAppAll(); //naver or db
		model.addAttribute("recommendList",recommendList);
		model.addAttribute("applicationList",applicationList);
		System.out.println(recommendList);
		return "/index";
	}
}