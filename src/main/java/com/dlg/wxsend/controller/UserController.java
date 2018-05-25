package com.dlg.wxsend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dlg.wxsend.service.UserService;
import com.github.pagehelper.PageInfo;

/*
 * 用户
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	/*
	 * 分页查询用户表
	 */
	@RequestMapping(value="/select",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public PageInfo getUser(
			@RequestParam("num")Integer pageNum,
			@RequestParam("size") Integer pageSize){
		//System.out.println(pageNum+"---"+pageSize);
		PageInfo info=userService.getUser(pageNum, pageSize);
		return info;
	}
}
