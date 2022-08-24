package com.acorn.dadockProject.dto;

import lombok.Data;

/*
 * 
 * mysql> desc wishList;
+-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| wishlist_no     | int          | NO   | PRI | NULL    | auto_increment |
| market_board_no | int          | YES  | MUL | NULL    |                |
| user_id         | varchar(255) | YES  | MUL | NULL    |                |
| jjim            | tinyint(1)   | YES  |     | NULL    |                |
+-----------------+--------------+------+-----+---------+----------------+
 * 
 * 
 */
@Data
public class WishList {
	public int wishlist_no;
	//public int market_board_no;
	private MarketBoard marketBoard;
	public String user_id;
	public boolean jjim;
}
