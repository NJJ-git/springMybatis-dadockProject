package com.acorn.dadockProject.dto;

import lombok.Data;

/*
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| comment_no | int          | NO   | PRI | NULL    | auto_increment |
| user_id    | varchar(255) | YES  | MUL | NULL    |                |
| isbn       | varchar(255) | YES  | MUL | NULL    |                |
| content    | varchar(255) | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
 */

@Data
public class Comment {
	public int comment_no;
	public String user_id;
	public String isbn;
	public String content;
}
