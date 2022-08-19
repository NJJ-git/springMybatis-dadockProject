package com.acorn.dadockProject.service;

import java.util.List;

import com.acorn.dadockProject.dto.User;

public interface UserService {

	List<User> getSearchList(String type, String keyword);
} 
