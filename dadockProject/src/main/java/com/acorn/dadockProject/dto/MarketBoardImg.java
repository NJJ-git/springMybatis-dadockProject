package com.acorn.dadockProject.dto;

import lombok.Data;

/*
 * mysql> desc market_board_img;
+---------------------+--------------+------+-----+---------+----------------+
| Field               | Type         | Null | Key | Default | Extra          |
+---------------------+--------------+------+-----+---------+----------------+
| market_board_img_no | int          | NO   | PRI | NULL    | auto_increment |
| market_board_no     | int          | YES  | MUL | NULL    |                |
| img_path            | varchar(255) | YES  |     | NULL    |                |
+---------------------+--------------+------+-----+---------+----------------+
 * */
@Data
public class MarketBoardImg {
	private int market_board_img_no;
	private int market_board_no; 
	private String img_path;
	 
	
}
