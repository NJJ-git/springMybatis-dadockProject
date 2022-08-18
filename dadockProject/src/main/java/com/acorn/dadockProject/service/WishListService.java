package com.acorn.dadockProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.dadockProject.mapper.WishListMapper;

@Service
public class WishListService {
	@Autowired WishListMapper wishListMapper;
	
}
