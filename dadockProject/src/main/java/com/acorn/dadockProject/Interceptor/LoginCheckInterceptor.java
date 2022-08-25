package com.acorn.dadockProject.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LoginCheck.preHandle : 해당 url을 요청하기 전");
		String prevPage=request.getHeader("Referer");
		if(prevPage==null) {
			prevPage = "/";
		}
		HttpSession session=request.getSession();
		if(session.getAttribute("loginUser")!=null) {
			return true;			
		}else {
			session.setAttribute("loginMsg", "로그인 후 이용 바랍니다.");
			session.setAttribute("redirectPage", prevPage);
			response.sendRedirect(prevPage);
			return false;
		}
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("LoginCheck.postHandle : 해당 url이 요청이 완료됨(응답 직전)");
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("LoginCheck.afterCompletion : 해당 url의 응답이 완료된 후(tymeleaf 동적 파일이 실행 완료)");
	}
}
