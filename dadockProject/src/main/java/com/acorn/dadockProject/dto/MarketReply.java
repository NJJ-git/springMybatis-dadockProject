package com.acorn.dadockProject.dto;
/*
 * mysql> desc market_reply;
+-----------------+--------------+------+-----+-------------------+-------------------+
| Field           | Type         | Null | Key | Default           | Extra             |
+-----------------+--------------+------+-----+-------------------+-------------------+
| market_reply_no | int          | NO   | PRI | NULL              | auto_increment    |
| contents        | varchar(255) | YES  |     | NULL              |                   |
| post_time       | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| market_board_no | int          | YES  | MUL | NULL              |                   |
| user_id         | varchar(255) | YES  | MUL | NULL              |                   |
+-----------------+--------------+------+-----+-------------------+-------------------+
 * */

import java.util.Date;

import lombok.Data;
@Data
public class MarketReply {
	private int market_reply_no;
	private String contents;
	private Date post_time;
	private int market_board_no;
	private String user_id;
	
	private User user;
}
