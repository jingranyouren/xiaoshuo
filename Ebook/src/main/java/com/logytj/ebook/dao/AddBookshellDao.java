package com.logytj.ebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.logytj.ebook.entity.AddBookshellEntity;

/**
* @author logytj 2020-08-13
*/
@Mapper
public interface AddBookshellDao {
	
	int insertSelective(AddBookshellEntity addBooksellEntity);
	
	List<AddBookshellEntity> selectBookShellByUserId(@Param("userId")Integer userId,
												     @Param("limit")int limit);
	
	AddBookshellEntity selectAddBookshellByPrimaryKey(@Param("userId")Integer userId,
													  @Param("bookId")Integer bookId);
	
	int deleteShellByprimaryKey(@Param("userId")Integer userId,
								@Param("bookId")Integer bookId);
	
	
}
