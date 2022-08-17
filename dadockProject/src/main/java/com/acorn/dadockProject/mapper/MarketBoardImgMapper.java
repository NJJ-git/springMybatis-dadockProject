package com.acorn.dadockProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.dadockProject.dto.MarketBoardImg;

//com.acorn.dadockProject.mapper.MarketBoardImgMapper
@Mapper
public interface MarketBoardImgMapper {
	int insertOne (MarketBoardImg marketBoardImg);
	List<MarketBoardImg> selectMarketBoardNo (int marketBoardNo);
	int selectCountMarketBoardNo(int marketBoardNo); //수정시 이미지 선택해서 삭제할떼
	MarketBoardImg selectOne(int marketBoardNo);
	int deleteOne (int marketBoardNo);
	
	
}
