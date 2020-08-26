package com.logytj.ebook.reader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logytj.ebook.common.Result;
import com.logytj.ebook.common.ResultGenerator;
import com.logytj.ebook.entity.UserEntity;
import com.logytj.ebook.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/login")
	public Result login(@RequestBody UserEntity user) {
		  UserEntity data = userService.login(user.getNickName());
		  if(data != null)
			  return ResultGenerator.genSuccess(data);
		  return ResultGenerator.genFailed(300,null);
	}
	
	@PostMapping("/user/register")
	public Result register(@RequestBody UserEntity user) {
		return ResultGenerator.genSuccess(userService.register(user));
	}
}
