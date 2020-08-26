package com.logytj.ebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logytj.ebook.entity.RootCategoryEntity;

@Mapper
public interface RootCategoryDao {
	List<RootCategoryEntity> selectRootCategory();
}
