package com.acorn.dadockProject.mapper;
//com.acorn.dadockProject.mapper.UserMapper

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.dadockProject.dto.User;
@Mapper
public interface UserMapper {
	List<User> selectPageAll();
	User selectOne(String userId);
	User selectPwOne(String userId, String pw);
	int deleteOne(String userId);
	int updateOne(User user);
	int registerOne(User user);
	List<User> selectSearchList(String type,String keyword);
}
