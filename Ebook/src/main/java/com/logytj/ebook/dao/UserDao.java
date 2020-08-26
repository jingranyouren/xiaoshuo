package com.logytj.ebook.dao;

import org.apache.ibatis.annotations.Mapper;

import com.logytj.ebook.entity.UserEntity;

@Mapper
public interface UserDao {
	
	int insertUser(UserEntity user);
	int insertUserSelective(UserEntity user);
	UserEntity selectUserByNickName(String nickName);
	
}
