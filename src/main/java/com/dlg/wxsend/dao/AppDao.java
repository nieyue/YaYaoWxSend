package com.dlg.wxsend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dlg.wxsend.bean.App;

/*
 * 公众号
 */
@Mapper
public interface AppDao {
	/*
	 * 新增公众号
	 */
	public boolean addApp(@Param("app") App app);
	
	/*
	 * 查询公众号
	 */
	public List<App> getApp();
	
	/*
	 * 根据appId查询公众号
	 */
	public App getAppByAppId(String AppId);
	
	/*
	 * 根据状态查询公众号
	 */
	//public List<App> getAppByStatus(Integer status);
	
	/*
	 * 根据状态更新公众号状态
	 */
//	public boolean updateAppByStatus(
//					@Param("newStatus") Integer newStatus,
//					@Param("oldStatus") Integer oldStatus);

	/*
	 * 根据appAccountId更新状态
	 */
//	public boolean updateStatusById(
//					@Param("appId")Integer appId,
//					@Param("status")Integer status);
	
}
