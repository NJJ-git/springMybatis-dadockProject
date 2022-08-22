package com.acorn.dadockProject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acorn.dadockProject.dto.Comment;
import com.acorn.dadockProject.mapper.CommentMapper;


@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentMapper commentMapper;
	
	@RequestMapping("insert.do")
	public String insert(Comment comment, HttpSession session) {
		int insert=0;
		String userId;
		insert=commentMapper.insertOne(comment);
		userId=(String) session.getAttribute("loginUser");
		if(insert>0) {
			return "redirect:/library/list/1";
		}else {
			return "redirect:/library/list/1";
		}
	}
}