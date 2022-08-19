package com.acorn.dadockProject.dto;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/*

  +------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| library_no | int          | NO   | PRI | NULL    | auto_increment |
| isbn       | varchar(255) | YES  |     | NULL    |                |
| user_id    | varchar(255) | YES  |     | NULL    |                |
| title      | varchar(255) | YES  |     | NULL    |                |
| author     | varchar(255) | YES  |     | NULL    |                |
| publisher  | varchar(255) | YES  |     | NULL    |                |
| end_date   | datetime     | YES  |     | NULL    |                |
| star       | int          | NO   |     | 0       |                |
+------------+--------------+------+-----+---------+----------------+
 
 */


@Data
public class Library {

	private int library_no;
	private String isbn;
	private String user_id;
	private String title;
	private String author;
	private String publisher;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date end_date;
	private int star;
	
}
