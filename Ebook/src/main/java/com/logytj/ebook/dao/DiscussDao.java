package com.logytj.ebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.logytj.ebook.entity.DiscussEntity;
import com.logytj.ebook.vo.DiscussVO;

@Mapper
public interface DiscussDao {
	int insertSelective(DiscussEntity discussEntity);
	
	List<DiscussVO> selectDiscussByBookId(@Param("bookId")Integer bookId,@Param("limit")int limit);
}
