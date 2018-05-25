package com.dlg.wxsend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dlg.wxsend.bean.App;
import com.dlg.wxsend.service.AppService;
import com.dlg.wxsend.util.StatusMsg;
import com.github.pagehelper.PageInfo;

/*
 * 公众号
 */
@RestController
@RequestMapping("/appAccount")
public class AppController {
	@Autowired
	private AppService appService;
	
	/*
	 * 新增公众号
	 */
	@RequestMapping("/add")
	@ResponseBody
	public StatusMsg addAppAccount(App account){
		System.out.println(account);
		StatusMsg msg=appService.addApp(account);
		return msg;
	}
	
	/*
	 * 分页查询公众号
	 */
	@RequestMapping("/select")
	@ResponseBody
	public PageInfo getAppAccount(
			@RequestParam(value="num",required=false,defaultValue="1")Integer num,
			@RequestParam(value="size",required=false,defaultValue="10")Integer size){
		//System.out.println(num+"---"+size);
		PageInfo info=appService.getApp(num, size);
		return info;
	}
	
	/*
	 * 查询所有公众号
	 */
	@RequestMapping("/apps")
	@ResponseBody
	public List<App> getApps(){
		List<App> list=appService.getApps();
		for(App app: list){
			System.out.println(app);
		}
		return list;
	}
}
