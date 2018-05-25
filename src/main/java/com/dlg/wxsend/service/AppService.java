package com.dlg.wxsend.service;

import java.util.List;

import com.dlg.wxsend.bean.App;
import com.dlg.wxsend.util.StatusMsg;
import com.github.pagehelper.PageInfo;

/*
 * 公众号
 */
public interface AppService {
	/*
	 * 新增公众号
	 */
	public StatusMsg addApp(App app);
	
	/*
	 * 分页查询所有公众号
	 */
	public PageInfo getApp(Integer pageNum,Integer pageSize);
	
	/*
	 * 查询所有
	 */
	public List<App> getApps();
	
	/*
	 * 根据appid获取App
	 */
	public App getAppByAppId(String appid);
	
}
