package com.acorn.dadockProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.acorn.dadockProject.Interceptor.LoginCheckInterceptor;

@Configuration
public class InterceptorConfig  implements WebMvcConfigurer{
	@Autowired
	LoginCheckInterceptor loginCheckInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginCheckInterceptor)
				.addPathPatterns("/user/**")
				.excludePathPatterns("/user/login.do")
				.excludePathPatterns("/user/signup.do")
				.addPathPatterns("/market/**")
				.excludePathPatterns("/market/goodsList/1")
				.excludePathPatterns("/market/goodsDetail/**");
				
		
	}
	
}
