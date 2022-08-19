package com.acorn.dadockProject.dto;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
+-------------+
| Field       |
+-------------+
| isbn        |
| title       |
| author      |
| img         |
| publisher   |
| pubdate     |
| description |
| link        |
| discount    |
+-------------+
 */

@Data
public class Book {
	private String isbn;
	private String title;
	private String author;
	private String image;
	private String publisher;
	private String pubdate;
	private String description;
	private String link;
}
