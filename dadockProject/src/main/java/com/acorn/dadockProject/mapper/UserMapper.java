package com.acorn.dadockProject.mapper;
//com.acorn.dadockProject.mapper.UserMapper

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.dadockProject.dto.User;
@Mapper
public interface UserMapper {
	List<User> selectPageAll(int startRow,int pageSize);
	int selectPageAllCount();
	User selectOne(String userId);
	User selectPwOne(String userId, String pw);
	int deleteOne(String userId);
	int updateOne(User user);
	int registerOne(User user);
}
