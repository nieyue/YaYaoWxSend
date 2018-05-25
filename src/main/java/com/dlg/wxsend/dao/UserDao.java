package com.dlg.wxsend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dlg.wxsend.bean.User;

/*
 * 用户表
 */
@Mapper
public interface UserDao {
	/*
	 * 查询表中的数据量
	 */
	public Integer getNum();
	
	/*
	 * 更新用户表中的用户状态
	 */
	public boolean updateStatus(Integer status);
	
	/*
	 * 根据openId和appid新用户表中的用户状态
	 */
	public boolean updateStatusByopenId(
			@Param("openId") String openId,
			@Param("appid") String appid,
			@Param("status") Integer status,
			@Param("channelId") Integer channelId);
			
	/*
	 * 新增用户
	 */
	public boolean addUser(@Param("user") User user);
	
	/*
	 * 根据openId和appid查询
	 */
	public User getUser(@Param("openId") String openId,@Param("appid") String appid);
	
	/*
	 * 分页查询用户
	 */
	public List<User> getUserByPageHelper();
	
}
