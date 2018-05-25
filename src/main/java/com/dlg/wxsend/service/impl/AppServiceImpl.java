package com.dlg.wxsend.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/*
 * 公众号
 */
import com.dlg.wxsend.bean.App;
import com.dlg.wxsend.dao.AppDao;
import com.dlg.wxsend.exception.SelfException;
import com.dlg.wxsend.service.AppService;
import com.dlg.wxsend.util.StatusMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class AppServiceImpl implements AppService{
	@Autowired
	private AppDao appDao;
	
	/*
	 * 新增公众号
	 */
	@Override
	public StatusMsg addApp(App app) {
		/*
		 * 对对象判空
		 */
		if(ObjectUtils.isEmpty(app)){
			return StatusMsg.backMsg(40000, "对象不能为空");
		}
		/*
		 * 对具体字段、进行判空
		 */
		if(StringUtils.isEmpty(app.getAppId().trim())){
			return StatusMsg.backMsg(40002, "AppId不能为空");
		}
		if(StringUtils.isEmpty(app.getSecret().trim())){
			return StatusMsg.backMsg(40002, "AppSecret不能为空");
		}
		if(StringUtils.isEmpty(app.getAppName().trim())){
			return StatusMsg.backMsg(40002, "公众号名称不能为空");
		}
		//AppId去重、判断表中是否已经存在AppId
		App app2=
				appDao.getAppByAppId(app.getAppId());
		if(!ObjectUtils.isEmpty(app2)){
			return StatusMsg.backMsg(40000, "该公众号已经存在");
		}
		//新增、默认状态是0(正常)、时间当前时间
		app.setCreateDate(new Date());
		app.setStatus(0);
		boolean b=appDao.addApp(app);
		if(!b){
			return StatusMsg.backMsg(40000, "新增失败");
		}
		return StatusMsg.success();
	}
	
	/*
	 * 分页查询所有公众号
	 */
	@Override
	public PageInfo getApp(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<App> list=appDao.getApp();
		PageInfo<App> info=new PageInfo<App>(list);
		for(App acc: list){
			System.out.println(acc);
		}
		return info;
	}
	
	/*
	 * 查询所有App
	 */
	@Override
	public List<App> getApps() {
		List<App> list=appDao.getApp();
		return list;
	}
	
	/*
	 * 根据appid查询对应的App
	 */
	@Override
	public App getAppByAppId(String appid) {
		App app=appDao.getAppByAppId(appid);
		if(ObjectUtils.isEmpty(app)){
			throw new SelfException("该公众号不存在");
		}
		return app;
	}

}
