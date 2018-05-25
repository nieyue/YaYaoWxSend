package com.dlg.wxsend.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.dlg.wxsend.bean.App;
import com.dlg.wxsend.bean.Info;
import com.dlg.wxsend.util.StatusMsg;

import net.sf.json.JSONArray;

/*
 * 微信接口
 * 	 1、生成网页授权的Url
 * 	 2、获取code、根据code获取openId
 */
public interface WeiXinService {
	/*
	 * 生成网页授权的Url
	 */
	public String createUrl(String redirectUri,String channelId,String appId);
	
	/*
	 * 微信网页授权获取openId、并更新渠道用户表
	 */
	public void getOpenId(String code,String state,HttpServletResponse resp);
	
	/*
	 * 获取access_token
	 */
	public String getAccessToken(App app);
	
	/*
	 * 获取关注用户的openId
	 */
	public JSONArray getOpenIdArr(String accessToken);
	
	/*
	 * 更新用户表
	 */
	public StatusMsg update(App app,String accessToken,JSONArray openIdArr);
	
	/*
	 * 客服-群发消息
	 */
	public StatusMsg sendInfo(String accessToken,JSONArray openIdArr,Info info,String appId);
	
}
