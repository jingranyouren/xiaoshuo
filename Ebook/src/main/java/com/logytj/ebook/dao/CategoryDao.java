package com.logytj.ebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logytj.ebook.entity.CategoryEntity;

@Mapper
public interface CategoryDao {
	List<CategoryEntity> selectCategoryBySelective(CategoryEntity category);
	List<CategoryEntity> selectCategoryByRootId(Integer rootId);
}
