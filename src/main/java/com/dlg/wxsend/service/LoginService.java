package com.dlg.wxsend.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dlg.wxsend.util.StatusMsg;


/*
 * 用户登录模块
 */
public interface LoginService {
	/*
	 * 获取验证码
	 */
	public void getCode(HttpSession session,HttpServletResponse resp);
	
	/*
	 * 登录验证
	 */
	public StatusMsg login(String userName,String password,String code,String role,HttpSession session);
	
	
}
