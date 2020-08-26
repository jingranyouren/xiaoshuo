package com.logytj.ebook.reader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logytj.ebook.common.Result;
import com.logytj.ebook.common.ResultGenerator;
import com.logytj.ebook.service.IndexConfigSevice;

@RestController
public class IndexConfigController {
	
	@Autowired
	private IndexConfigSevice indexConfigService;
	
	@GetMapping("/indexconfig")
	public Result indexItem(@RequestParam("sex")String sex) {
		if("男".equals(sex.trim()) || "女".equals(sex.trim()))
				return ResultGenerator.genSuccess(indexConfigService.getItemForIndex(sex));
		return ResultGenerator.genFailed();
	}
	
	@GetMapping("/more")
	public Result more(@RequestParam("sex")String sex,@RequestParam("configType")Integer configType) {
		if(("男".equals(sex.trim()) || "女".equals(sex.trim()))&& (configType > 0 && configType < 5))
			return ResultGenerator.genSuccess(indexConfigService.getMorebyConfigTypeAndSex(configType, sex));
		return ResultGenerator.genFailed("参数错误");
			
	}
}
