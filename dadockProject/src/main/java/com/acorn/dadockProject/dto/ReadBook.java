package com.acorn.dadockProject.dto;

import java.util.Date;

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
| discount    | int          | YES  |     | NULL    |       |
| star        | int          | NO   |     | 0       |       |
+-------------+--------------+------+-----+---------+-------+
 
 */
@Data
public class ReadBook {
	private Long isbn;
	private String title;
	private String author;
	private String img;
	private String publisher;
	private Date pubdate;
	private String description;
	private String link;
	private int discount;
	private int star;
}
