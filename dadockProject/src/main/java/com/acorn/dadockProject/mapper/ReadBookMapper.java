package com.acorn.dadockProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.acorn.dadockProject.dto.Library;
import com.acorn.dadockProject.dto.ReadBook;

@Mapper
public interface ReadBookMapper {
	   public List<ReadBook> selectReadBookByStar();
	   public List<ReadBook> selectByAppRecommendAll();
	   public List<ReadBook> selectByIdReadBookAndLibrary(String user_id);
	   public List<ReadBook> selectOneByIdReadBookAndLibrary(String user_id, int library_no);
	   
	   public int selectOneByUserAndIsbnInLibrary(String user_id, String isbn);
	   public int insertOne(ReadBook readBook);
	   public Library selectStar(String isbn);
	   
	}
