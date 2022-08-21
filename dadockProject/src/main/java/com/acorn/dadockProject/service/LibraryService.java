package com.acorn.dadockProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.dadockProject.mapper.LibraryMapper;

@Service
public class LibraryService {
	@Autowired LibraryMapper libraryMapper;
	
	public int removeLibrary(int libraryNo) throws Exception{
		int remove = 0;
		remove=libraryMapper.deleteOne(libraryNo);
		return remove;
	}
}
