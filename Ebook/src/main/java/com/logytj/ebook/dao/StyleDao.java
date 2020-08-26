package com.logytj.ebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.logytj.ebook.entity.StyleEntity;

@Mapper
public interface StyleDao {
	List<StyleEntity> selectStyleByBookId(@Param("bookId")Integer bookId,@Param("limit")int limit);
}
