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
		List<ReadBook> recommendList=readBookMapper.selectReadBookByStar(); // 별점 평균 순 출력
		List<ReadBook> dadockList=readBookMapper.selectByAppRecommendAll(); // 담은 유저 count 순 출력
		model.addAttribute("recommendList",recommendList);
		model.addAttribute("dadockList",dadockList);
		return "/index";
	}
}