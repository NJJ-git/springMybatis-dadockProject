package com.acorn.dadockProject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acorn.dadockProject.dto.User;
import com.acorn.dadockProject.mapper.UserMapper;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/list/{page}")
	public String list(@PathVariable int page, Model model) {
		List<User> userList=userMapper.selectPageAll();
		System.out.println(userList);
		model.addAttribute("userList",userList);
		return "/user/list";
	}
	@GetMapping("/detail/{userId}")
	public String detail(@PathVariable String userId , Model model) {
		User user=userMapper.selectOne(userId);
		model.addAttribute(user);
		System.out.println(user);
		return "/user/detail";
	}
	@PostMapping("/update.do")
	public String update(User user) {
		int update=0;
		update=userMapper.updateOne(user);
		if(update>0) {
			return "redirect:/user/list/1";
		}else {
			return "redirect:/user/detail/"+user;
		}
	}
	@GetMapping("/delete/{userId}")
	public String delete(@PathVariable String userId) {
		int delete=0;
		try {
			delete=userMapper.deleteOne(userId);
		} catch (Exception e) {e.printStackTrace();}
		if(delete>0) {
			return "redirect:/user/list/1";
		}else {
			return "redirect:/user/detail/"+userId;
		}
	}
	@GetMapping("/adminRegister.do")
	public void adminRegister() {};
	
	@PostMapping("/adminRegister.do")
	public String adminRegister(User user) {
		int register=0;
		try {
			register=userMapper.registerOne(user);
		} catch (Exception e) {e.printStackTrace();}
		if(register>0) {
			return "redirect:/user/list/1";
		}else {
			return "redirect:/user/adminRegister.do";
		}
	}
	@GetMapping("/login.do")
	public void login() {}
	@PostMapping("/login.do")
	public String login(
			@RequestParam(value = "user_id")String userId,
			@RequestParam(value = "pw")String pw,
			HttpSession session
			) {
		User user=null;
		try {
			user=userMapper.selectPwOne(userId, pw);
		} catch (Exception e) {e.printStackTrace();}
		System.out.println(user);
		if(user!=null) {
			session.setAttribute("loginUser", user);
			Object redirectPage=session.getAttribute("redirectPage");
			session.removeAttribute("redirectPage");//계속 redirectPage로 갈 수 없게 removeAttribute 
			if(redirectPage!=null) {
				return "redirect:"+redirectPage;
			}
			return "redirect:/";
		}else {
			return "redirect:/user/login.do";
		}
	}
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		//session.invalidate();
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	@GetMapping("/signup.do")//회원가입 페이지 생성하기 위한 매핑
	public void signup() {}//public void signup() 하면 페이지가 생긴다
	@PostMapping("/signup.do")
	public void signup(User user) {
		System.out.println(user);
	}
	
}


































