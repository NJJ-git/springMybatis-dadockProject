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
	 public int market_board_img_no;
	 public int market_board_no; 
	 public String img_path;
	 
	
}
