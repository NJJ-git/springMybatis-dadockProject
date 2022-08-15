package com.acorn.dadockProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acorn.dadockProject.dto.UserDto;
import com.acorn.dadockProject.mapper.UserMapper;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/list/{page}")
	public String list(@PathVariable int page, Model model) {
		List<UserDto>userList=(userMapper.selectAll());
		System.out.println(userList);
		model.addAttribute(userList);
		
		return "/user/list";
	}
	@GetMapping("/register.do")
	public void register() {};
	
	@PostMapping("/register.do")
	public String register(UserDto userDto) {
		int regist=0;
		regist=userMapper.insertOne(userDto);
		if(regist>0) {
			return "redirect:/";
		}else {
			return "redirect:/user/register.do";
		}
	}
	@GetMapping("/delete.do/{userId}")
	public String delete(@PathVariable String userId) {
		int delete=0;		
		try {
			delete=userMapper.deleteOne(userId);				
		} catch (Exception e) {	e.printStackTrace();}
	if(delete>0) {
			return "redirect:/user/list/1";
	} else {
			return "redirect:/user/detail/"+userId;
	}
  }
}










