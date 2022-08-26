package com.acorn.dadockProject.dto;
/*
 * mysql> desc market_board; market_board_img_no
mysql> desc market_board;
+-----------------+--------------+------+-----+-------------------+-------------------+
| Field           | Type         | Null | Key | Default           | Extra             |
+-----------------+--------------+------+-----+-------------------+-------------------+
| market_board_no | int          | NO   | PRI | NULL              | auto_increment    |
| user_id         | varchar(255) | YES  | MUL | NULL              |                   |
| title           | varchar(255) | YES  |     | NULL              |                   |
| post_time       | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| img_path        | varchar(255) | YES  |     | NULL              |                   |
| content         | text         | YES  |     | NULL              |                   |
| book_title      | varchar(255) | YES  |     | NULL              |                   |
| isbn            | varchar(255) | YES  | MUL | NULL              |                   |
| address         | varchar(255) | YES  |     | NULL              |                   |
| price           | int          | YES  |     | NULL              |                   |
| state           | int			 | YES  |     | NULL              |                   |
+-----------------+--------------+------+-----+-------------------+-------------------+
 * 
 */
/**/
import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public  class MarketBoard {
	private int market_board_no; //pk
	private String title;
	private Date post_time;
	private String img_path; //
	private String content;
	private String  book_title;
	private String isbn;
	private int price;
	private int state;

	private String user_id; // user테이블에서 가져와야하나? 아닌가? 몰라
	private String address;
	
	private User user; //n:1 조인 fk는 user_id
	//private MarketBoardImg marketBoardImg;
	private List<MarketBoardImg> marketBoardImgs;
	
	private List<MarketReply> marketReplys;
	private WishList lgoinUserWishList;
	private int MreplysSize;
	////조인..//
	
	
}
