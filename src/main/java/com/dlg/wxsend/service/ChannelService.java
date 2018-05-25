package com.dlg.wxsend.service;

import java.util.List;

import com.dlg.wxsend.bean.Channel;
import com.dlg.wxsend.bean.Link;
import com.dlg.wxsend.util.StatusMsg;
import com.github.pagehelper.PageInfo;

/*
 * 渠道
 */
public interface ChannelService {
	/* 
	 * 新增渠道
	 */
	public StatusMsg addChannel(Channel channel);
	
	/*
	 * 查询所有渠道信息
	 */
	public PageInfo getChannelList(Integer pageNum,Integer pageSize);
	
	/*
	 * 生成推广渠道
	 */
	public List<Link> getLink(Integer channelid,String channelname);
}
