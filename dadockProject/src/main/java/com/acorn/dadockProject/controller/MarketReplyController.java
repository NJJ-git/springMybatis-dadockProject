package com.acorn.dadockProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acorn.dadockProject.dto.MarketReply;
import com.acorn.dadockProject.mapper.MarketReplyMapper;

@Controller
@RequestMapping("/reply")
public class MarketReplyController {
	@Autowired
	MarketReplyMapper marketReplyMapper;
	
	@PostMapping("/insert.do")
	public String insert(
			MarketReply marketReply){
		int insert=0;
		String msg;
		try {
			insert=marketReplyMapper.insertOne(marketReply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			msg="댓글등록성공";
		}
		return "redirect:/market/goodsDetail"+marketReply.getMarket_board_no();
		
	}
}
