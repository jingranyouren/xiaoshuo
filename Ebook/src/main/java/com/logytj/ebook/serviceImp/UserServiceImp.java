package com.logytj.ebook.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logytj.ebook.dao.UserDao;
import com.logytj.ebook.entity.UserEntity;
import com.logytj.ebook.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public UserEntity register(UserEntity user) {
		// TODO Auto-generated method stub
        	userDao.insertUser(user);
        return userDao.selectUserByNickName(user.getNickName());
	}
	@Override
	public UserEntity login(String nickName) {
		// TODO Auto-generated method stub
		return userDao.selectUserByNickName(nickName);
	}

}
