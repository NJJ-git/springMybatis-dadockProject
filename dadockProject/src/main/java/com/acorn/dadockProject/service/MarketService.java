package com.acorn.dadockProject.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acorn.dadockProject.dto.MarketBoard;
import com.acorn.dadockProject.dto.MarketBoardImg;
import com.acorn.dadockProject.mapper.MarketBoardImgMapper;
import com.acorn.dadockProject.mapper.MarketMapper;
@Service
public class MarketService {

	@Value("${spring.servlet.multipart.location}")  
	public String savePath;
	@Autowired MarketMapper marketMapper;	
	@Autowired MarketBoardImgMapper marketBoardImgMapper;
	
	public int uploadBoard(MarketBoard marketBoard) throws Exception {
		int upload=0;
		upload=marketMapper.insertOne(marketBoard);
		int imgUpload=0;
		if(upload>0 && marketBoard.getMarketBoardImgs()!=null) {
			for(MarketBoardImg marketBoardImg : marketBoard.getMarketBoardImgs()) {
				marketBoardImg.setMarket_board_no(marketBoard.getMarket_board_no());
				imgUpload+=marketBoardImgMapper.insertOne(marketBoardImg);
			}
		}
		System.out.println("보드 등록 :"+upload);
		System.out.println("보드 이미지 등록 :"+imgUpload);
		return upload;
	}
	
	@Transactional
	public int updateMBoardDelteMBoardImg(MarketBoard marketBoard, int [] markemarketBoardNos) {
		int update=0;
		if(markemarketBoardNos!=null) {
			for(int no: markemarketBoardNos) {
				MarketBoardImg marketBoardImg=marketBoardImgMapper.selectOne(no);
				File f=new File(savePath+"/"+marketBoardImg.getImg_path());
				System.out.println("MBoard의 이미지 파일 삭제:"+f.delete());
				int removeMBoardImg=marketBoardImgMapper.deleteOne(no);
				System.out.println("Mboard의 market_board_img 삭제:" +removeMBoardImg);
			}
		}
		if(marketBoard.getMarketBoardImgs()!=null) {
			for(MarketBoardImg marketBoardImg : marketBoard.getMarketBoardImgs()) {
				int registMBoardImg=marketBoardImgMapper.insertOne(marketBoardImg);
				System.out.println("Mboard의 Board_img 등록:"+registMBoardImg);
			}
		}
		update=marketMapper.updateOne(marketBoard);
		
		return update;
	}
	
	public int removeMBoard(int marketBoardNo) throws Exception{
		int remove=0;
		int removeImg=0;
		//나중에 리플도..추가해야함 하...
		List<MarketBoardImg> marketBoardImgs=marketBoardImgMapper.selectMarketBoardNo(marketBoardNo);
		if(marketBoardImgs!=null) {
			marketBoardImgs.stream()
			.map(MarketBoardImg::getImg_path)
			.forEach((img)->{
				File f=new File(savePath+"/"+img);
				System.out.println("보드 이미지 삭제:"+f.delete());
			});
		}
		remove=marketMapper.deleteOne(marketBoardNo);
		return remove;
		
		
		
		
		
		
		
	}
	
}







