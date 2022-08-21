package com.acorn.dadockProject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.dadockProject.dto.MarketReply;
@Mapper
//com.acorn.dadockProject.mapper.MarketReplyMapper
public interface MarketReplyMapper {
	MarketReply selectOne(int marketBoardNo);
	int insertOne(MarketReply marketReply);
	int updateOne(MarketReply marketReply);
	int deleteOne(int marketBoardNo);
}
