package com.dlg.wxsend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlg.wxsend.bean.User;
import com.dlg.wxsend.dao.UserDao;
import com.dlg.wxsend.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/*
 * 用户
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public PageInfo getUser(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<User> list=userDao.getUserByPageHelper();
		PageInfo info=new PageInfo(list);
		for(User user: list){
			System.out.println(user);
		}
		return info;
	}

}
