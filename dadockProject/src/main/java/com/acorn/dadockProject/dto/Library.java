package com.acorn.dadockProject.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/*

+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| library_no | int          | NO   | PRI | NULL    | auto_increment |
| user_id    | varchar(255) | YES  |     | NULL    |                |
| isbn       | varchar(255) | YES  |     | NULL    |                |
| end_date   | datetime     | YES  |     | NULL    |                |
| star       | int          | NO   |     | 0       |                |
+------------+--------------+------+-----+---------+----------------+
 
 */


@Data
public class Library {
	private int library_no;
	private String user_id;
	private String isbn;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date end_date;
	private int star;
	
}
