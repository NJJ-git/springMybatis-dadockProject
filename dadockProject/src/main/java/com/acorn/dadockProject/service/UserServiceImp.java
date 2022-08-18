package com.acorn.dadockProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.dadockProject.dto.User;
import com.acorn.dadockProject.mapper.UserMapper;
@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserMapper userMapper;

	@Override
	public List<User> getSearchList(String type, String keyword) {
		return userMapper.selectSearchList(type, keyword);
	}

}
