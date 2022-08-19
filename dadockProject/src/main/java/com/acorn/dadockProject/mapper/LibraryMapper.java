package com.acorn.dadockProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.dadockProject.dto.Library;

@Mapper
public interface LibraryMapper {
	
	List<Library> selectAll();
	
	Library selectOne(int libraryNo);
	int insertOne(Library library);
	int updateOne(Library library);
	int deleteOne(int libraryNo);
}
