package com.acorn.dadockProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.dadockProject.dto.Comment;

@Mapper
public interface CommentMapper {
	public List<Comment> selectByIsbnAll(String isbn);
	public List<Comment> selectByUserAll(String user_id);
	
	public int insertOne(Comment comment);
	public int updateOne(Comment comment);
	public int deleteOne(int comment_no);
}
