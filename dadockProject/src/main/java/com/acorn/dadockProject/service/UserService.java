package com.acorn.dadockProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acorn.dadockProject.dto.User;

@Service
public interface UserService {
	List<User> getSearchList(String type, String keyword);
}
