package com.acorn.dadockProject.controller;

import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import com.acorn.dadockProject.dto.Book;
import com.acorn.dadockProject.dto.Library;
import com.acorn.dadockProject.dto.MarketBoard;
import com.acorn.dadockProject.dto.MarketBoardImg;
import com.acorn.dadockProject.dto.Paging;
import com.acorn.dadockProject.dto.WishList;
import com.acorn.dadockProject.mapper.MarketBoardImgMapper;
import com.acorn.dadockProject.mapper.MarketMapper;
import com.acorn.dadockProject.mapper.ReadBookMapper;
import com.acorn.dadockProject.mapper.WishListMapper;
import com.acorn.dadockProject.service.BookApiCallService;
import com.acorn.dadockProject.service.MarketService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	@Autowired WishListMapper wishListMapper;
	
	///책검색 ㅌㅅㅌ///
	@Autowired
	BookApiCallService bookApiCallService;
	@Autowired
	ObjectMapper objectMapper;
	
	
	@GetMapping("/goodsList/{page}")
	public String goodsList (@PathVariable int page,Model model) {
		List<MarketBoard> goodsList=marketMapper.selectAll();
		System.out.println("goodsList: 출력쩜"+goodsList);
		model.addAttribute("goodsList",goodsList);
		return "/market/goodsList";
	}
	
	
	
	@GetMapping("/goodsInsertSearch1") //책 검색
	public void goodsInsertSearch1() {
		
	}
	
	
	@GetMapping("/goodsInsertSearch/{isbn}") // insert
	public String goodsInsertSearch(@PathVariable String isbn, Book book,
			Model model) throws Exception {
		JSONArray naver_result_arr=new JSONArray();

		JSONObject naver_result = bookApiCallService.get("https://openapi.naver.com/"
				+ "v1/search/book.json?query='"+isbn+"'");
		
		naver_result_arr=(JSONArray) naver_result.get("items");
		
		// JSONArray 파싱
		String jsonBookArray=naver_result.get("items").toString();
		
		List<Book> bookDetails=objectMapper.readValue(jsonBookArray, new TypeReference<List<Book>>(){});
		Book bookDetail=bookDetails.get(0);
		model.addAttribute("bookDetails", bookDetails);
		System.out.println(bookDetail);
		
		return "/market/goodsInsertSearch";
	}
	
    @PostMapping("/goodsInsertSearch.do")
    public String goodsInsertSearch(MarketBoard marketBoard) {
    		int insert=0;
    		insert=marketMapper.insertOne(marketBoard);
    		if(insert>0) {
    			return "redirect:/market/goodsList/1";
    		}else {
    			return "redirect:/market/goodsList/1";
    		}
    	}
	
	
	
	@GetMapping("/goodsInsert.do") //로그인 폼 만들면 세션 추가해서 로그인 한 사람만..등록가능하게 하기
	public void goodsInsert (){
		
	}
	
	@PostMapping("/goodsInsert.do")
	public String insert(int state,
				MarketBoard marketBoard,
				List<MultipartFile> imgFiles
){
		System.out.println("marketBoardinsert"+marketBoard);
		System.out.println("savePathinsert"+savePath);
			
				System.out.println("state"+state);
			
		
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
	//modelㅇ
	@GetMapping("/goodsDetail/{marketBoardNo}")
	public String goodsDetail(@PathVariable int marketBoardNo, Model model) {
		
		MarketBoard marketBoard=marketMapper.selectOne(marketBoardNo);
		System.out.println("marketBoard Detail 출력:"+marketBoard);
		model.addAttribute("marketBoard",marketBoard);
		
		return "/market/goodsDetail";
		
	}
	
	@GetMapping("/goodsUpdate/{marketBoardNo}") //로그인 추가 
	public String goodsUpdate (@PathVariable int marketBoardNo , Model model) {
		  MarketBoard marketBoard=marketMapper.selectOne(marketBoardNo);
		  
		  model.addAttribute(marketBoard);
		 return "/market/goodsUpdate" ;
		 
		
	}
	@PostMapping("/goodsUpdate.do")
	public String goodsUpdate (
			MarketBoard marketBoard,
			@RequestParam(name = "MarketboardImgNo",required = false) int[] marketBoardImgNos,
			@RequestParam(name = "imgFile",required = false) MultipartFile[]imgFiles) {
		int update=0;
		System.out.println(Arrays.toString(marketBoardImgNos)); //삭제할이미지 번호들
		System.out.println(Arrays.toString(imgFiles));
		System.out.println("업데이트:"+marketBoard);
		try {
			int mBoardImgCount=marketBoardImgMapper.selectCountMarketBoardNo(marketBoard.getMarket_board_no());
			int insertMBoardImgLength=MARKET_BOARD_IMG_LIMIT-mBoardImgCount+((marketBoardImgNos!=null)?marketBoardImgNos.length:0);
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
			update=marketService.updateMBoardDelteMBoardImg(marketBoard, marketBoardImgNos);
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
	//user_id는 나중에 user쪽 작업 끝나면 추가하기! "/wishList/{userId}/{marketBoardNo}/{jjim}/"
	/*
	 * @GetMapping("/wishList/{marketBoardNo}/{jjim}") public String wishListInsert
	 * (
	 * 
	 * @PathVariable int marketBoardNo,
	 * 
	 * @PathVariable boolean jjim, Model model) { String msg=""; WishList
	 * wishList=new WishList(); wishList.setMarket_board_no(marketBoardNo);
	 * wishList.setJjim(jjim); int insert=0; try {
	 * insert=wishListMapper.addWishlist(wishList); if(insert>0) { if(jjim=true)
	 * {msg="찜 성공";} }else { if(jjim=false) {msg="찜 실패";} } } catch (Exception e) {
	 * e.printStackTrace(); if(jjim=false) {msg="찜 실패(오류)";} }
	 * model.addAttribute("msg",msg); return "redirect:/market/goodsList/1"; }
	  */
  @GetMapping("/marketPay/{marketBoardNo}") //결제페이지
  	public String marketPay (
  			@PathVariable int marketBoardNo, 
  			Model model) { 
	  MarketBoard marketBoard;
	  marketBoard=marketMapper.selectOne(marketBoardNo);
	  model.addAttribute("marketBoard",marketBoard); 
	  return "/market/marketPay"; 
	  }
	 
	
	@GetMapping("/marketOrder/{marketBoardNo}") // 결제완료 페이지 /marketOrder/{marketBoardNo}로 넘기기..
	public String marketOrder (
			@PathVariable int marketBoardNo,
			Model model) {
		MarketBoard marketBoard;
		marketBoard=marketMapper.selectOne(marketBoardNo);
		model.addAttribute("marketBoard",marketBoard); 
		return "/market/marketOrder";
		
	}
	
	@GetMapping("/marketPayDetail/{marketBoardNo}") //결제 완료 후 상세내역
	public String marketPayDeatail (
			@PathVariable int marketBoardNo,
			Model model) {
		MarketBoard marketBoard;
		marketBoard=marketMapper.selectOne(marketBoardNo);
		model.addAttribute("marketBoard",marketBoard); 
		return "/market/marketPayDetail";
		
	}
	@GetMapping("/marketUserDetail")
	public void marketUserDetail () {
	}
	
	@PostMapping("/wishList.do")
	public String wishList (MarketBoard marketBoard) {
		int insert=0;
		insert=marketMapper.insertOne(marketBoard);
		if(insert>0) {
			return "redirect:/market/wishList/1";
		}else {
			return "redirect:/market/goodsList/1";
		}
	}
	@GetMapping("/wishList/{page}")
	public String marketWishList (@PathVariable int page,Model model) {
		List<MarketBoard> wishList=marketMapper.selectAll();
		System.out.println("wishList:"+wishList);
		model.addAttribute("wishList",wishList);
		return "/market/goodsList";
	}
	
	@GetMapping("/insert/{isbn}")
	public String insert(@PathVariable String isbn, Book book,
			Model model) throws Exception {
		JSONArray naver_result_arr=new JSONArray();

		JSONObject naver_result = bookApiCallService.get("https://openapi.naver.com/"
				+ "v1/search/book.json?query='"+isbn+"'");
		
		naver_result_arr=(JSONArray) naver_result.get("items");
		
		// JSONArray 파싱
		String jsonBookArray=naver_result.get("items").toString();
		
		List<Book> bookDetails=objectMapper.readValue(jsonBookArray, new TypeReference<List<Book>>(){});
		Book bookDetail=bookDetails.get(0);
		model.addAttribute("bookDetails", bookDetails);
		System.out.println(bookDetail);
		
		return "/market/insert";
	}
	
	@PostMapping("/wishListInsert.do")
	public String insert(MarketBoard marketBoard
		) {
		int insert=0;
		String userId;
		try {
			insert=marketService.uploadBoard(marketBoard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			return "redirect:/market/wishList/1";
		}else {
			return "redirect:/market/wishList/1";
		}
	}
	
}
