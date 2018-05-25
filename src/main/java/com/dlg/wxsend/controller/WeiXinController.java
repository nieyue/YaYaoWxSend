package com.dlg.wxsend.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dlg.wxsend.bean.App;
import com.dlg.wxsend.bean.Info;
import com.dlg.wxsend.exception.SelfException;
import com.dlg.wxsend.service.AppService;
import com.dlg.wxsend.service.WeiXinService;
import com.dlg.wxsend.util.AccessTokenSingle;
import com.dlg.wxsend.util.StatusMsg;

import net.sf.json.JSONArray;

/*
 * 对接微信公众号接口
 * 		1、获取code
 * 		2、微信网页授权
 * 		3、
 */
@RestController
@RequestMapping("/wx")
public class WeiXinController {
	//private static final String URL="http://ccsd.boya1.cn/wx/openid";
	//private static final String URL="http://192.168.7.16:8011/wx/openid";
	
	@Autowired
	private WeiXinService weiXinService;
	
	@Autowired
	private AppService appService;
	
	/*
	 * 获取code
	 * 	 获取页面的域名：生成redirect_uri
	 * 	生成微信授权的URL、返回给页面
	 */
	@RequestMapping(value="/code",method=RequestMethod.GET)
	@ResponseBody
	public StatusMsg getCode(HttpServletRequest request){
		/*StringBuffer url=request.getRequestURL();
		String redirectUri=
				url.delete(url.length()-request.getRequestURI().length(), url.length()).toString();*/
		String localUrl = request.getHeader("Referer");
		System.out.println(localUrl);
		String channelId=localUrl.substring(localUrl.indexOf("=")+1,localUrl.indexOf("&"));
		String appId=localUrl.substring(localUrl.lastIndexOf("=")+1);
		System.out.println(channelId+"---"+appId);
		//对redirectUri进行URLEncoder编码
		String redirectUri;
		try {
			redirectUri=URLEncoder.encode("http://ccsd.boya1.cn/wx/openid", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new SelfException("redirectUri进行URLEncoder编码失败");
		}
		System.err.println(redirectUri);
		String autoUrl=weiXinService.createUrl(redirectUri,channelId,appId);
		
		System.err.println(autoUrl);
		return StatusMsg.backMsg(200, autoUrl); 
	}
	
	/*
	 * 根据code，获取隐示授权用户的openId
	 */
	@RequestMapping(value="/openid",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public void getOpenId(
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="state",required=false)String state,
			HttpSession session,
			HttpServletResponse response,
			HttpServletRequest req ) throws IOException{
		/*ChannelUser channelUser=(ChannelUser) session.getAttribute("channelUser");
		System.out.println(channelUser);*/
		/*if(channelUser==null){
			try {
				response.sendRedirect("http://ccsd.boya1.cn/link.html?channelId="+state);
				//req.getRequestDispatcher("http://www.baidu.com").forward(req, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		System.err.println(code+"-1-2-"+state);
		weiXinService.getOpenId(code,state,response); 
	}
	
	/*
	 * 更新用户数据
	 */
	@RequestMapping("/update")
	@ResponseBody
	public StatusMsg updateUser(@RequestParam("appId") String appId){
		//System.out.println(appId);
		if(StringUtils.isEmpty(appId.trim())){
			return StatusMsg.backMsg(40000, "请先选择公众号");
		}
		/*
		 * 根据appid查询获取对应的App
		 */
		App app=appService.getAppByAppId(appId);
		if(ObjectUtils.isEmpty(app)){
			return StatusMsg.failure();
		}
		/*
		 * 通过accessToken单例
		 * 	1、判断是否已经生成accessToken
		 *  2、判断生成的accessToken是否过期
		 */
		long tempTime=AccessTokenSingle.getAccessToken().getTempTime();
		long nowTime=System.currentTimeMillis();
		String accessToken=null;
		//accessToken已经存在或者未过期(在3600秒以内、微信过期时间是7200秒)
		if(tempTime!=0 && (nowTime-tempTime)/1000<=3600){
			accessToken=AccessTokenSingle.getAccessToken().getToken();
		}else{
			//accessToken不存在、或者已经过期
			//获取AccessToken
			accessToken=weiXinService.getAccessToken(app);
		}
		//获取JSONArray的openId数组
		JSONArray openIdArr=weiXinService.getOpenIdArr(accessToken);
		//更新
		StatusMsg msg=weiXinService.update(app, accessToken, openIdArr);
		
		return msg;
	}
	
	/*
	 * 推送图文消息
	 */
	@RequestMapping(value="/send",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public StatusMsg sendInfo(Info info,String appId){
		System.out.println(info);
		System.out.println(appId+"--------");
		if(StringUtils.isEmpty(appId.trim())){
			return StatusMsg.backMsg(40000, "请选择公众号");
		}
		if(ObjectUtils.isEmpty(info)){
			return StatusMsg.failure();
		}
		/*
		 * 根据appid查询获取对应的App
		 */
		App app=appService.getAppByAppId(appId);
		if(ObjectUtils.isEmpty(app)){
			return StatusMsg.failure();
		}
		//获取AccessToken
		String accessToken=weiXinService.getAccessToken(app);
		//获取JSONArray的openId数组
		JSONArray openIdArr=weiXinService.getOpenIdArr(accessToken);
		
		weiXinService.sendInfo(accessToken, openIdArr, info, appId);
		return null;
	}
	
}
