package com.acorn.dadockProject.mapper;
//com.acorn.dadockProject.mapper.WishListMapper

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.dadockProject.dto.WishList;

@Mapper
public interface WishListMapper {
	//찜 추가
	public int addWishList (WishList wishList);
	//찜 목록 리스트
	public List<WishList> selectWishList(String userId) ;
	//삭제"selectWishList"
	public int deleteWhishList(int wishlistNo);
	//체크되어있는지확인
	public WishList checkWishList(WishList wishList);
	
	
}