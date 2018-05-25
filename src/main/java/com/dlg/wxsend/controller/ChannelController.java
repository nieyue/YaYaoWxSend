package com.dlg.wxsend.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dlg.wxsend.bean.Channel;
import com.dlg.wxsend.bean.Link;
import com.dlg.wxsend.service.ChannelService;
import com.dlg.wxsend.util.StatusMsg;
import com.github.pagehelper.PageInfo;

/*
 * 渠道
 */
@RestController
@RequestMapping("/channel")
public class ChannelController {
	@Autowired
	private ChannelService channelService;
	
	/*
	 * 新增渠道
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public StatusMsg addChannel(Channel channel,HttpServletRequest request){
		/*StringBuffer url=request.getRequestURL();
		String realUrl=
				url.delete(url.length()-request.getRequestURI().length(),url.length()).toString(); 
		System.out.println(realUrl);*/
		/*
		 * 判空
		 * 		1、对对象判空
		 * 		2、对不能为空的字段进行判空
		 */
		boolean b=ObjectUtils.isEmpty(channel);
		if(b){
			return StatusMsg.failure();
		}
		if(StringUtils.isEmpty(channel.getName().trim())){
			return StatusMsg.backMsg(40000, "名称不能为空");
		}
		if(StringUtils.isEmpty(channel.getPhone().trim())){
			return StatusMsg.backMsg(40000, "账号(phone)不能为空");
		}
		
		StatusMsg msg=channelService.addChannel(channel);
	
		return msg;
	}
	
	/*
	 * 分页查询所有渠道
	 */
	@RequestMapping(value="/select",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo getChnnelList(
			@RequestParam(value="num")Integer pageNum,
			@RequestParam(value="size")Integer pageSize){
		System.out.println(pageNum+"----"+pageSize);
		PageInfo info=channelService.getChannelList(pageNum, pageSize);
		return info;
	}
	
	/*
	 * 生成推广链接
	 */
	@RequestMapping(value="/link",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<Link> getLink(
			@RequestParam("channelid") Integer channelid,
			@RequestParam("channelname") String channelname){
		List<Link> linkList=channelService.getLink(channelid,channelname);
		System.out.println(linkList);
		return linkList;
	}
	
}
