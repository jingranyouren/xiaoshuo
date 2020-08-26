package com.logytj.ebook.service;

import java.util.List;

import com.logytj.ebook.entity.BookEntity;
import com.logytj.ebook.entity.CategoryEntity;
import com.logytj.ebook.vo.CategoryVO;
import com.logytj.ebook.vo.MoreBookVO;

public interface CategoryService {
	List<CategoryVO> rootCategoryls();
	
	List<CategoryVO> selectiveCategoryls(CategoryEntity category);
	
	List<MoreBookVO> selectiveBookls(BookEntity book,Integer rootId);
}
