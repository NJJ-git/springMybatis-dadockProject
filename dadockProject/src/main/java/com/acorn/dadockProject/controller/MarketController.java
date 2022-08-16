package com.acorn.dadockProject.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import com.acorn.dadockProject.dto.MarketBoard;
import com.acorn.dadockProject.dto.MarketBoardImg;
import com.acorn.dadockProject.mapper.MarketBoardImgMapper;
import com.acorn.dadockProject.mapper.MarketMapper;
import com.acorn.dadockProject.service.MarketService;

//application.proferties에 입력하고 가져와야함!

@Controller
@RequestMapping("/market") 
public class MarketController {
	private final static int MARKET_BOARD_IMG_LIMIT=3;
	@Value("${spring.servlet.multipart.location}")  // 근데 왜 안되냐  아.. 위치 잘못되어있었음..class안에
	public String savePath;
	@Autowired MarketMapper marketMapper;
	@Autowired MarketService marketService;
	@Autowired MarketBoardImgMapper marketBoardImgMapper;
	
	@GetMapping("/goodsList/{page}")
	public String goodsList (@PathVariable int page,Model model) {
		List<MarketBoard> goodsList=marketMapper.selectAll();
		System.out.println("goodsList: 출력쩜"+goodsList);
		model.addAttribute("goodsList",goodsList);
		return "/market/goodsList";
	}
	
	@GetMapping("/goodsInsert.do") //로그인 폼 만들면 세션 추가해서 로그인 한 사람만..등록가능하게 하기
	public void goodsInsert() {}
	
	@PostMapping("/goodsInsert.do")
	public String insert(MarketBoard marketBoard,
			List<MultipartFile> imgFiles){
		System.out.println("marketBoardinsert"+marketBoard);
		System.out.println("savePathinsert"+savePath);
		
		
		int insert=0;
		try {
			if(imgFiles!=null) {
				List<MarketBoardImg> marketBoardImgs=new ArrayList<MarketBoardImg>();
				for(MultipartFile imgFile:imgFiles) {
					String type=imgFile.getContentType();
					if(type.split("/")[0].equals("image")) {
						String newFileName="market_goods"+System.nanoTime()+"."+type.split("/")[1];
						Path newFilePath=Paths.get(savePath+"/"+newFileName);
						imgFile.transferTo(newFilePath);
						MarketBoardImg marketBoardImg=new MarketBoardImg();
						marketBoardImg.setImg_path(newFileName);
						marketBoardImgs.add(marketBoardImg);
						System.out.println(marketBoardImgs);
					}
				}
				if(marketBoardImgs.size()>0) {
					marketBoard.setMarketBoardImgs(marketBoardImgs);
					System.out.println("!!이미지marketBoardImgs"+marketBoardImgs);
				}
			}
			insert=marketService.uploadBoard(marketBoard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			return "redirect:/market/goodsList/1";
		}else {
			return "redirect:/market/goodsInsert.do";
		}
	}
	
	@GetMapping("/goodsDetail/{marketBoardNo}")
	public String goodsDetail(@PathVariable int marketBoardNo, Model model) {
		
		MarketBoard marketBoard=marketMapper.selectOne(marketBoardNo);
		System.out.println("marketBoard 출력:"+marketBoard);
		model.addAttribute("marketBoard",marketBoard);
		
		return "/market/goodsDetail";
		
	}
	
	@GetMapping("/goodsUpdate/{marketBoardNo}") //로그인 추가 
	public String goodsUpdate (@PathVariable int marketBoardNo) {
		
		  MarketBoard marketBoard=marketMapper.selectOne(marketBoardNo);
		 return "/market/goodsUpdate" ;
		 
		
	}
	@PostMapping("/goodsUpdate.do")
	public String goodsUpdate (
			MarketBoard marketBoard,
			@RequestParam(name = "MarketboardImgNo",required = false) int[] marketBoardNos,
			@RequestParam(name = "imgFile",required = false) MultipartFile[]imgFiles) {
		int update=0;
		System.out.println(Arrays.toString(marketBoardNos)); //삭제할이미지 번호들
		System.out.println(Arrays.toString(imgFiles));
		System.out.println("업데이트:"+marketBoard);
		try {
			int mBoardImgCount=marketBoardImgMapper.selectCountMarketBoardNo(marketBoard.getMarket_board_no());
			int insertMBoardImgLength=MARKET_BOARD_IMG_LIMIT-mBoardImgCount+((marketBoardNos!=null)?marketBoardNos.length:0);
			if(imgFiles!=null && insertMBoardImgLength>0) {
				List<MarketBoardImg> mBoardImgs=new ArrayList<MarketBoardImg>();
				for(MultipartFile imgFile : imgFiles) {
					String type=imgFile.getContentType();
					if(type.split("/")[0].equals("image")) {
						String newFileName="market_goods"+System.nanoTime()+"."+type.split("/")[1];
						Path newFilePath=Paths.get(savePath+"/"+newFileName);
						imgFile.transferTo(newFilePath);
						MarketBoardImg marketBoardImg=new MarketBoardImg();
						marketBoardImg.setImg_path(newFileName);
						mBoardImgs.add(marketBoardImg);
						if(--insertMBoardImgLength == 0) break;
					}
				}
				if(mBoardImgs.size()>0) {
					marketBoard.setMarketBoardImgs(mBoardImgs);
				}
			}
			update=marketService.updateMBoardDelteMBoardImg(marketBoard, marketBoardNos);
		} catch (Exception e) {e.printStackTrace();}
		if(update>0) {
			return "redirect:/market/goodsDetail/"+marketBoard.getMarket_board_no();
		}else {
			return "redirect:/market/goodsUpdate/"+marketBoard.getMarket_board_no();
			
		}
	}
	@GetMapping("/goodsDelete/{marketBoardNo}") //유저아이디 추가
	public String goodsDelete(
			@PathVariable int marketBoardNo
			) {
		int delete=0;
		try {
			delete=marketService.removeMBoard(marketBoardNo);
		} catch (Exception e) {e.printStackTrace();}
		if(delete>0) {
			return "redirect:/market/goodsList/1";
		}else {
			return "redirect:/market/goodsDetail/"+marketBoardNo;
			
		}
	}
	
	@GetMapping("/marketWishlist")
	public void marketWishlist () {
	}
	@GetMapping("/marketUserDetail")
	public void marketUserDetail () {
	}
	@GetMapping("/marketOrder")
	public void marketOrder () {
	}
	@GetMapping("/marketPay")
	public void marketPay () {
	}
	@GetMapping("/marketPayDetail")
	public void marketPayDeatail () {
	}
}
