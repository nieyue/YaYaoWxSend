package com.dlg.wxsend.service.impl;
import java.util.ArrayList;
/*
 * 渠道
 */
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.dlg.wxsend.bean.Account;
import com.dlg.wxsend.bean.App;
import com.dlg.wxsend.bean.Channel;
import com.dlg.wxsend.bean.Link;
import com.dlg.wxsend.bean.Role;
import com.dlg.wxsend.dao.AccountDao;
import com.dlg.wxsend.dao.AppDao;
import com.dlg.wxsend.dao.ChannelDao;
import com.dlg.wxsend.dao.RoleDao;
import com.dlg.wxsend.exception.CommonException;
import com.dlg.wxsend.exception.SelfException;
import com.dlg.wxsend.service.ChannelService;
import com.dlg.wxsend.util.MD5Code;
import com.dlg.wxsend.util.StatusMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ChannelServiceImpl implements ChannelService{
	//private static final String URL="http://ccsd.boya1.cn";
	private static final String URL="http://192.168.7.16:8011";
	
	@Autowired
	private ChannelDao channelDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private AppDao appDao;
	/*
	 * 新增：
	 * 		1、新增渠道
	 * 		2、根据渠道信息新增账号
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public StatusMsg addChannel(Channel channel) {
		//1、先判断渠道是否已经存在
		Integer count=channelDao.getCountByPhone(channel.getPhone());
		if(count>0){
			return StatusMsg.backMsg(40000, "该账号(phone)已经存在");
		}
		channel.setCreateDate(new Date());
		boolean b=channelDao.addChannel(channel);
		if(!b){
			throw new SelfException("新增渠道失败");
		}
		/*
		 * 新增渠道账户：
		 * 		新增一个渠道就生成一个账号、密码默认123456
		 * 		如果新增失败回滚事物
		 */
		//1、根据角色名、查询角色(注：此处应该动态从页面获取、目前是写死为只能添加"渠道主"账号)
		Role role=roleDao.getRole("渠道主");
		boolean roleBool=ObjectUtils.isEmpty(role);
		if(roleBool){
			throw new CommonException("查询角色信息失败");
		}
		//2、生成渠道账号
		Account account=new Account();
		account.setName(channel.getName());
		account.setPhone(channel.getPhone());
		account.setPassword(MD5Code.getMD5("123456"));
		account.setStatus(0);
		account.setRoleId(role.getRoleId());   
		account.setRoleName(role.getRoleName());
		account.setCreateDate(new Date());
		//根据账号(phone)查询该账号中是否已经存 
		boolean accBool=accountDao.addAccount(account);
		if(!accBool){
			throw new SelfException("该账户已经存在");
		}
		return StatusMsg.success();
	}
	
	/*
	 * 分页查询所有渠道
	 */
	@Override
	public PageInfo getChannelList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Channel> list=channelDao.getChannelList();
		PageInfo info=new PageInfo<>(list);
		for(Channel cl: list){
			System.out.println(cl);
		}
		return info;
	}
	
	/*
	 * 生成推广渠道
	 */
	@Override
	public List<Link> getLink(Integer channelid,String channelname) {
		List<Link> linkList=new ArrayList();
		List<App> list=appDao.getApp();
		for(App appAcc: list){
			String linkUrl=
					URL+"/link.html?channelId="+channelid+"&appId="+appAcc.getAppId();
			Link link=new Link(channelname, appAcc.getAppName(), linkUrl);
			linkList.add(link);
		}
		
		return linkList;
	}	
}
