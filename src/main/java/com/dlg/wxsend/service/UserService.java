package com.dlg.wxsend.service;

import com.github.pagehelper.PageInfo;

/*
 * 用户
 */
public interface UserService {
	/*
	 * 分页查询用户
	 */
	public PageInfo getUser(Integer pageNum,Integer pageSize);
}
