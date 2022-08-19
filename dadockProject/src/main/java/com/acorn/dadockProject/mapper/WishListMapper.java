package com.acorn.dadockProject.mapper;
//com.acorn.dadockProject.mapper.WishList

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.dadockProject.dto.WishList;

@Mapper
public interface WishListMapper {
	//찜 추가
	public int addWishlist (WishList wishList);
	//삭제
	public int deleteWhishList(int wishlistNo);
	//찜 목록
	public List<WishList> selectWishList (String userId);
	//확인
	public WishList checkWishList(WishList wishList);
	
	
}