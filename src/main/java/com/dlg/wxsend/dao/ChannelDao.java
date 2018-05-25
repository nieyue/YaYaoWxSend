package com.dlg.wxsend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dlg.wxsend.bean.Channel;


/*
 * 渠道CRUD
 */
@Mapper
public interface ChannelDao {
	/*
	 * 新增渠道
	 */
	public boolean addChannel(@Param("channel") Channel channel);
	
	/*
	 * 根据channelId修改渠道
	 */
	public boolean updateChannel(@Param("channel") Channel channel);
	
	/*
	 * 分页查询渠道
	 */
	public List<Channel> getChannelList();
	
	/*
	 * 根据channelId查询渠道
	 */
	public Channel getChannelByChannelId(Integer channelId);
	
	/*
	 * 根据phone查询用户数量
	 */
	public Integer getCountByPhone(String phone);
	
}
