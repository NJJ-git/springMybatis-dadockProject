package com.acorn.dadockProject.mapper;
//com.acorn.dadockProject.mapper.MarketMapper

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.dadockProject.dto.MarketBoard;

@Mapper
public interface MarketMapper {
	List<MarketBoard> selectAll();
	List<MarketBoard> selectPageAll(int startRow,int pageSize);
	int selectPageAllCount();
	List<MarketBoard> selectWishListAll(String loginUserId);

	int insertOne(MarketBoard marketBoard);
	MarketBoard selectOne (int marketBoardNo);
	int updateOne(MarketBoard marketBoard);
	int deleteOne(int marketBoardNo);
	int insertOne(int state);
	MarketBoard selectSerachOne(int marketBoardNo);
	
	List<MarketBoard> selectUserId(String userId);
	
	

}
