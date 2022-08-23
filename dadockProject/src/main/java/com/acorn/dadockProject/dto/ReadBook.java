package com.acorn.dadockProject.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/*
+-------------+--------------+------+-----+---------+-------+
| Field       | Type         | Null | Key | Default | Extra |
+-------------+--------------+------+-----+---------+-------+
| isbn        | varchar(255) | NO   | PRI | NULL    |       |
| title       | varchar(255) | YES  |     | NULL    |       |
| author      | varchar(255) | YES  |     | NULL    |       |
| img         | varchar(255) | YES  |     | NULL    |       |
| publisher   | varchar(255) | YES  |     | NULL    |       |
| pubdate     | date         | YES  |     | NULL    |       |
| description | text         | YES  |     | NULL    |       |
| link        | varchar(255) | YES  |     | NULL    |       |
+-------------+--------------+------+-----+---------+-------+
 
 */
@Data
public class ReadBook {
	private String isbn;
	private String title;
	private String author;
	private String img;
	private String publisher;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date pubdate;
	private String description;
	private String link;
	
	private List<Library> libraries;
}
