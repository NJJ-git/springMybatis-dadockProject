package com.acorn.dadockProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.acorn.dadockProject.dto.UserDto;

@Mapper
public interface UserMapper {
	public List<UserDto> selectAll();
	public UserDto selectOne(@Param(value="user_id") int user_id);
	public int updateOne(@Param(value ="userDto") UserDto userDto);
	public int insertOne(UserDto userDto);
	public int deleteOne(String user_id);
}
