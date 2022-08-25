package com.acorn.dadockProject.service;

import java.io.File;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.acorn.dadockProject.dto.User;
import com.acorn.dadockProject.mapper.UserMapper;
@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserMapper userMapper;
	@Value("${spring.servlet.multipart.location}")
	String savePath;

	@Override
	public List<User> getSearchList(String type, String keyword) {
		return userMapper.selectSearchList(type, keyword);
	}
	
	public int modifyUserRemoveImg(User user) {
		int modify=0;
		String profileImg=user.getProfile_img();
		if(profileImg!=null) { //선택한 삭제될 board_img.board_img_no
				File f=new File(savePath+"/"+profileImg); // 여기서 저장이 맞는거 같음.
				System.out.println("user의 profile_img 파일 삭제: "+f.delete());
		//여기서는 파일만 삭제하는거 같은데	
		}
		if(user.getProfile_img()!=null) {
			
		}
		modify=userMapper.updateOne(user);
		return modify;
	}

}
