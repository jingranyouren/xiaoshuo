package com.logytj.ebook.service;

import com.logytj.ebook.entity.UserEntity;

public interface UserService {
	public UserEntity register(UserEntity user);
	
	public UserEntity login(String nickName);
}
