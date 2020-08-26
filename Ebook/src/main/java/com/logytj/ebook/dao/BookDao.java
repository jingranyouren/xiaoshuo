package com.logytj.ebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.logytj.ebook.entity.BookEntity;
import com.logytj.ebook.entity.CategoryEntity;

@Mapper
public interface BookDao {
	BookEntity selectBookByPrimaryKey(Integer bookId);
	
	List<BookEntity> selectBookSelective(@Param("list")List<CategoryEntity> list ,@Param("book")BookEntity book);
	
	List<BookEntity> selectBooKByFreeStatus(Integer freeStatus);
	
	List<BookEntity> selectBookByupdateStatus(Integer updateStatus);
	
	List<BookEntity> selectBookByKeyword(@Param("keyword")String keyword);
}
