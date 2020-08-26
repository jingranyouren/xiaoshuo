package com.logytj.ebook.reader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logytj.ebook.common.Result;
import com.logytj.ebook.common.ResultGenerator;
import com.logytj.ebook.entity.BookEntity;
import com.logytj.ebook.entity.CategoryEntity;
import com.logytj.ebook.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/rootCategory")
	public Result rootCategory() {
		return ResultGenerator.genSuccess(categoryService.rootCategoryls());
	}
	@GetMapping("/category")
	public Result category(@RequestParam("rootId")Integer rootId,
			@RequestParam("categoryId")Integer categoryId) {
		CategoryEntity category = new CategoryEntity();
		category.setCategoryId(categoryId);
		category.setRootId(rootId);
		return ResultGenerator.genSuccess(categoryService.selectiveCategoryls(category));
	}
	@PostMapping("/category/book")
	public Result cateBook(@RequestBody BookEntity book,Integer rootId) {  //不校验数据合法性了
//		System.out.println("rootId "+rootId);
//		System.out.println("f "+book.getFreeStatus());
//		System.out.println("u "+book.getUpdateStatus());
//		System.out.println("c "+book.getCategotyId());
		return ResultGenerator.genSuccess(categoryService.selectiveBookls(book,rootId));
	}
}
