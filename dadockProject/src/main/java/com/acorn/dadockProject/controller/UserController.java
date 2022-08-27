package com.acorn.dadockProject.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.acorn.dadockProject.dto.IdCheck;
import com.acorn.dadockProject.dto.Paging;
import com.acorn.dadockProject.dto.User;
import com.acorn.dadockProject.mapper.UserMapper;
import com.acorn.dadockProject.service.UserServiceImp;

@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserMapper userMapper;
	
	@Value("${spring.servlet.multipart.location}")
	String savePath;
	
	@Autowired
	   UserServiceImp userService;

     private int modify;
	
	@GetMapping("/list/{page}")
	public String list(@PathVariable int page, @SessionAttribute (name = "loginUser", required=false) User loginUser,
			HttpSession session,
			Model model) {
		
		int row=7;
		int startRow=(page-1)*row;
		List<User> userList=userMapper.selectPageAll(startRow,row);
		int rowCount=userMapper.selectPageAllCount();
		Paging paging=new Paging(page, rowCount, "/user/list/",row);
			if(loginUser.getUser_id().equals("admin")){
				model.addAttribute("paging",paging);
				model.addAttribute("userList",userList);
				model.addAttribute("row",row);
				model.addAttribute("rowCount",rowCount);
				model.addAttribute("page",page);
				return "/user/list";
			}
		session.setAttribute("msg", "관리자만 이용 가능합니다.");
		return "redirect:/";
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
	@GetMapping("/modify/{userId}")
		public String modify(@PathVariable String userId, Model model) {
			User user=userMapper.selectOne(userId);
			model.addAttribute("user",user);
			System.out.println(user);
			return "/user/modify";
	}
	
	@PostMapping("/modify.do")
	public String modify(User user, 
			@SessionAttribute(required = false) User loginUser,
			MultipartFile imgFile,
			HttpSession session) {
		if(loginUser!=null && loginUser.getUser_id().equals(user.getUser_id())) {
			int modify=0;
		    String msg="";
				if(imgFile!=null && !imgFile.isEmpty()) {
					String[] types=imgFile.getContentType().split("/");
					if(types[0].equals("image")) {
						if(user.getProfile_img()!=null) {
							File file=new File(savePath+"/"+user.getProfile_img());
							boolean del=file.delete();
							System.out.println("기존 이미지 삭제"+del);
						}
						String newFileName="user_"+System.nanoTime()+"."+types[1];
						Path path=Paths.get(savePath+"/"+newFileName);
						try {
							imgFile.transferTo(path);
						} catch (IllegalStateException | IOException e) {
							e.printStackTrace();
						}
						user.setProfile_img(newFileName);
					}
				}
		    	modify=userMapper.modifyOne(user);
		    	msg=(modify>0)?"수정 성공!":"수정 실패!";
		    	session.setAttribute("msg", msg);
		    return "redirect:/user/profile/"+user.getUser_id();
		}else {
			return "/";
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
	@GetMapping("/profile/{user_id}")
	public String profile(@PathVariable String user_id , Model model) {
		User user=userMapper.selectOne(user_id);
     	model.addAttribute("user",user);
		System.out.println(user);
		return "/user/profile";
	}

	
	@GetMapping("/login.do")
	public void login(HttpServletRequest req) {
		System.out.println(req.getHeader("Referer"));
	}
	@PostMapping("/login.do")
	public String login(
			@RequestParam(value = "user_id")String userId,
			@RequestParam(value = "pw")String pw,
			HttpSession session,
			HttpServletRequest request
			) {
		String prevPage=request.getHeader("Referer");

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
			session.setAttribute("loginMsg", "아이디 혹은 비밀번호가 틀립니다");
			return "redirect:"+prevPage;
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
			return "redirect:/user/signup.do";
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
	@GetMapping("/getSearchList/{page}")
	   private String getSerchList(@RequestParam(value="type", required = false) String type,
		     @PathVariable int page,
	         @RequestParam(value="keyword", required = false) String keyword, Model model) throws Exception{
	     
		List<User> getSerchList= userService.getSearchList(type,keyword);
	      System.out.println(getSerchList);
	      
	      //페이징
	      int row=10;
	      int startRow=(page-1)*row;
	      int rowCount=userMapper.selectPageAllCount();
	      
	      Paging paging = new Paging(page, rowCount, "/user/getSearchList/", row);
	      
	      model.addAttribute("paging",paging);
	      model.addAttribute("rowCount",rowCount);
	      model.addAttribute("row",row);
	      model.addAttribute("page",page);
	      model.addAttribute("startRow",startRow);
	      model.addAttribute("userList", getSerchList);
	      
	      return "/user/list"; 
	   }
}


































