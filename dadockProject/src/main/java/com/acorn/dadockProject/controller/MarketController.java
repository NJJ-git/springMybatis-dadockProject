package com.acorn.dadockProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/market")
public class MarketController {
	@GetMapping("/marketMain")
	public void marketMain () {
	}
	@GetMapping("/marketWishlist")
	public void marketWishlist () {
	}
	@GetMapping("/marketUserDetail")
	public void marketUserDetail () {
	}
	@GetMapping("/goodsDetail")
	public void goodsDetail () {
	}
	@GetMapping("/marketOrder")
	public void marketOrder () {
	}
	@GetMapping("/marketPay")
	public void marketPay () {
	}
}
