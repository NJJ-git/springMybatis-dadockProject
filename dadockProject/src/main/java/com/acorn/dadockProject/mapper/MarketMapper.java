package com.acorn.dadockProject.mapper;
//com.acorn.dadockProject.mapper.MarketMapper

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.dadockProject.dto.MarketBoard;

@Mapper
public interface MarketMapper {
	List<MarketBoard> selectAll();
	int insertOne(MarketBoard marketBoard);
	MarketBoard selectOne (int marketBoardNo);
	int updateOne(MarketBoard marketBoard);
	int deleteOne(int marketBoardNo);
	int insertOne(int state);
	MarketBoard selectSerachOne(int marketBoardNo);
	
	
}
