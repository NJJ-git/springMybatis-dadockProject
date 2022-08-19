package com.acorn.dadockProject.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/*
+----------------+--------------+------+-----+-------------------+-------------------+
| Field          | Type         | Null | Key | Default           | Extra             |
+----------------+--------------+------+-----+-------------------+-------------------+
| user_id        | varchar(255) | NO   | PRI | NULL              |                   |
| pw             | varchar(255) | YES  |     | NULL              |                   |
| name           | varchar(255) | YES  |     | NULL              |                   |
| signup         | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| email          | varchar(255) | YES  | UNI | NULL              |                   |
| phone          | varchar(255) | YES  | UNI | NULL              |                   |
| address        | varchar(255) | YES  |     | NULL              |                   |
| address_detail | varchar(255) | YES  |     | NULL              |                   |
| level          | int          | YES  |     | NULL              |                   |
| point          | int          | YES  |     | NULL              |                   |
| profile_img    | varchar(255) | YES  |     | NULL              |                   |
| type           | varchar(255) | YES  |     | NULL              |                   |
| keyword        | varchar(255) | YES  |     | NULL              |                   |
+----------------+--------------+------+-----+-------------------+-------------------+*/
@Data
public class User {
	private String user_id;
	private String pw;
	private String name;
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date signup;
	private String email;
	private String phone;
	private String address;
	private String address_detail;
	private String profile_img;
	private int level;
	private int point;
	
	//검색
	private String type; //
	private String keyword; //검색 내용
}
