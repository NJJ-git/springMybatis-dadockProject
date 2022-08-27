package com.acorn.dadockProject.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.acorn.dadockProject.dto.MarketReply;
import com.acorn.dadockProject.dto.User;
import com.acorn.dadockProject.mapper.MarketReplyMapper;

@Controller
@RequestMapping("/reply")
public class MarketReplyController {
	@Autowired
	MarketReplyMapper marketReplyMapper;
	
	@PostMapping("/insert.do")
	public String insert(
			MarketReply marketReply,
			@SessionAttribute User loginUser
			){
			marketReply.setUser_id(loginUser.getUser_id());
			System.out.println(marketReply);
			int insert=0;
			String msg="등록 실패";
			try {
				insert=marketReplyMapper.insertOne(marketReply);
			} catch (Exception e) {
				e.printStackTrace();
				msg="등록에러 새로고침 후 다시 시도하세요.";
			}
			if(insert>0) {
				msg="댓글등록성공";
				
			}
			return "redirect:/market/goodsDetail/"+marketReply.getMarket_board_no();
		
	}
	@PostMapping("/update.do") //기존파일 삭제 후 업데이트!
	public String update(
			MarketReply marketReply,
			@SessionAttribute(required = false)User loginUser,
			HttpSession session) {
		if(loginUser!=null && loginUser.getUser_id().equals(marketReply.getUser().getUser_id())) {
			int update=0;
			String msg="";
			try {
				update=marketReplyMapper.updateOne(marketReply);
			} catch (Exception e) {e.printStackTrace();}
			
			msg=(update>0)?"수정 성공!":"수정 실패!";
			return "redirect:/market/goodsDetail/"+marketReply.getMarket_board_no();
			}else {
				return "redirect:/user/login.do";
			}
	}
		
	@GetMapping("/delete/{marketReplyNo}")
	public String delete(
			@PathVariable int marketReplyNo,
			@SessionAttribute(required = false)User loginUser,
			HttpSession session) {
		MarketReply marketReply=marketReplyMapper.selectOne(marketReplyNo);
		System.out.println("marketReply 삭제:"+marketReply);
		if(loginUser!=null && loginUser.getUser_id().equals(marketReply.getUser().getUser_id())) {
			int delete=0;
			try {
				delete=marketReplyMapper.deleteOne(marketReplyNo);
			} catch (Exception e) {e.printStackTrace();}
			if(delete>0) {
				session.setAttribute("msg", "댓글 삭제 성공");
			}else {
				session.setAttribute("msg", "댓글 삭제 실패");
			}
			return "redirect:/market/goodsDetail/"+marketReply.getMarket_board_no();
		}else {
			return "redirect:/user/login.do";
		}
	}
}
