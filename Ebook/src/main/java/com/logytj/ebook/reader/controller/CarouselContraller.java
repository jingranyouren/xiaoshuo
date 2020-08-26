package com.logytj.ebook.reader.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logytj.ebook.common.Constants;
import com.logytj.ebook.common.Result;
import com.logytj.ebook.common.ResultGenerator;
import com.logytj.ebook.service.CarouselService;

@RestController
public class CarouselContraller {
	
	@Autowired
	private CarouselService carouselService;
	
	@GetMapping("/carousel")
	public Result getCarouselForIndex(@RequestParam("sex")String sex){
		if("男".equals(sex.trim())||"女".equals(sex.trim()))
			return ResultGenerator.genSuccess(carouselService.getCarouselBySex(sex, Constants.CAROUSEL_NUM));
		return ResultGenerator.genFailed("参数错误");
	}
}
