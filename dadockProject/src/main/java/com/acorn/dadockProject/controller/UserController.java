package com.acorn.dadockProject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.dadockProject.dto.IdCheck;
import com.acorn.dadockProject.dto.Paging;
import com.acorn.dadockProject.dto.User;
import com.acorn.dadockProject.mapper.UserMapper;

@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/list/{page}")
	public String list(@PathVariable int page, Model model) {
		int row=7;
		int startRow=(page-1)*row;
		List<User> userList=userMapper.selectPageAll(startRow,row);
		int rowCount=userMapper.selectPageAllCount();
		
		Paging paging=new Paging(page, rowCount, "/user/list/",row);
		model.addAttribute("paging",paging);
		model.addAttribute("userList",userList);
		model.addAttribute("row",row);
		model.addAttribute("rowCount",rowCount);
		model.addAttribute("page",page);
		System.out.println(userList);
		return "/user/list";
	}
	@GetMapping("/detail/{userId}")
	public String detail(@PathVariable String userId , Model model) {
		System.out.println(userId);
		User user=userMapper.selectOne(userId);
		model.addAttribute("user",user);
		System.out.println(user);
		return "/user/detail";
	}
	@PostMapping("/update.do")
	public String update(User user) {
		int update=0;
		System.out.println(user);
		update=userMapper.updateOne(user);
		if(update>0) {
			return "redirect:/user/list/1";
		}else {
			return "redirect:/user/detail/"+user.getUser_id();
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
	public void login(HttpServletRequest req) {
		System.out.println(req.getHeader("Referer"));
	}
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
			session.removeAttribute("redirectPage");
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
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	//회원가입 문제 해결..
	@GetMapping("/signup.do")
	public void signup() throws Exception{};
	@PostMapping("/signup.do")
	public String signup(User user) throws Exception{
		System.out.println(user);
		int signup=0;
		try {
			signup=userMapper.registerOne(user);
		} catch (Exception e) {e.printStackTrace();}
		if(signup>0) {
			return "redirect:/";
		}else {
			return "redirect:/signup.do";
		}
	}
	@GetMapping("/idCheck/{userId}")
	public @ResponseBody IdCheck idCheck(@PathVariable String userId) {
		IdCheck idCheck=new IdCheck();
		System.out.println(userId);
		User user=userMapper.selectOne(userId);
		if(user!=null) {
			idCheck.idCheck=true;
			idCheck.user=user;
		}
		return idCheck;
	}
}


































