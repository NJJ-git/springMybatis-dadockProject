package com.acorn.dadockProject.dto;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Library {

	private int library_no;
	private String title;
	private String author;
	private String publisher;
	
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date read_date;
	
}
