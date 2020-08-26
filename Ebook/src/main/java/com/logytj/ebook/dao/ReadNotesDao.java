package com.logytj.ebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.logytj.ebook.entity.ReadNotesEntity;

@Mapper
public interface ReadNotesDao {
	List<ReadNotesEntity> selectNotesByUserId(@Param("userId")Integer id,
			@Param("limit")int limit);
	ReadNotesEntity selectNotesByBookIdAndUserID(@Param("bookId")Integer bookId,
			@Param("userId")Integer userId);
	
	int insertBySelective(ReadNotesEntity notes);
	
	int updateLast(ReadNotesEntity notes);
}
