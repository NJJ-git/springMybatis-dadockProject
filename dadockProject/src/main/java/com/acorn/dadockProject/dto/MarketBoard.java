package com.acorn.dadockProject.dto;
/*
 *  market_board_img_no 이거 새로 추가했는데 괜히햇ㄴㅏ
 * mysql> desc market_board; market_board_img_no
+---------------------+--------------+------+-----+-------------------+-------------------+
| Field               | Type         | Null | Key | Default           | Extra             |
+---------------------+--------------+------+-----+-------------------+-------------------+
| market_board_no     | int          | NO   | PRI | NULL              | auto_increment    |
| user_id             | varchar(255) | YES  | MUL | NULL              |                   |
| title               | varchar(255) | YES  |     | NULL              |                   |
| post_time           | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| img_path            | varchar(255) | YES  |     | NULL              |                   |
| content             | text         | YES  |     | NULL              |                   |
| book_title          | varchar(255) | YES  |     | NULL              |                   |
| isbn                | bigint       | YES  | MUL | NULL              |                   |
| address             | varchar(255) | YES  |     | NULL              |                   |
| price               | int          | YES  |     | NULL              |                   |
| market_board_img_no | int          | YES  |     | NULL              |                   |
+---------------------+--------------+------+-----+-------------------+-------------------+
 * 
 */
/**/
import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class MarketBoard {
	public int market_board_no; //pk
	//public int market_board_img_no; //fk 이거 지우기..
	public String user_id; // user테이블에서 가져와야하나? 아닌가? 몰라
	public String title;
	public Date post_time;
	public String img_path; //
	public String content;
	public String  book_title;
	public int isbn;
	public String address;
	public int price;
	
	//private MarketBoardImg marketBoardImg;
	private List<MarketBoardImg> marketBoardImgs;
	
	
	////조인..//
	
	
}
