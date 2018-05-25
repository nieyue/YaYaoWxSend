package com.dlg.wxsend.service.impl;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.dlg.wxsend.bean.App;
import com.dlg.wxsend.bean.Channel;
import com.dlg.wxsend.bean.Info;
import com.dlg.wxsend.bean.TempUser;
import com.dlg.wxsend.bean.User;
import com.dlg.wxsend.dao.AppDao;
import com.dlg.wxsend.dao.ChannelDao;
import com.dlg.wxsend.dao.TempUserDao;
import com.dlg.wxsend.dao.UserDao;
import com.dlg.wxsend.exception.SelfException;
import com.dlg.wxsend.service.WeiXinService;
import com.dlg.wxsend.util.AccessTokenSingle;
import com.dlg.wxsend.util.HttpClientUtil;
import com.dlg.wxsend.util.StatusMsg;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/*
 * 微信接口
 * 	 1、生成网页授权的Url
 * 	 2、获取code、根据code获取授权用户的open_id
 * 	 3、获取access_token
 * 	 4、获取关注用户的open_id
 * 	 5、根据open_id获取用户信息
 * 	 6、发送消息(群发)
 */
@Service
public class WeiXinServiceImpl implements WeiXinService {
	@Value("${location.grant_type}")
	private String gt;
	
	@Autowired
	private AppDao appDao;
	
	@Autowired
	private ChannelDao channelDao;
	
	@Autowired
	private TempUserDao tempUserDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TempUser tempUser;
	
	@Autowired
	private User user;
	
	/*
	 * 生成网页授权的Url
	 * 
	 */
	//https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect 	
	@Override
	public String createUrl(String redirectUri, String channelId, String appId) {
		//用渠道id和appid组合生成state
		String state=channelId+"*"+appId;
		String url=
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+appId
				+"&redirect_uri="
				+redirectUri
				+"&response_type=code&scope=snsapi_base&state="
				+state+"#wechat_redirect"; 
		return url;
	}
	
	/*
	 * 微信网页授权：获取隐示授权的用户的openId、并更新临时用户表
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void getOpenId(String code,String state,HttpServletResponse resp) {
		//根据state获取appid和channelId
		String channelId=state.substring(0,state.lastIndexOf("*"));
		String appid=state.substring(state.lastIndexOf("*")+1);
		//根据appid获取secret
		App app=appDao.getAppByAppId(appid);
		if(ObjectUtils.isEmpty(app)){
			throw new SelfException("该公众号不存在");
		}
		System.out.println(appid+"---"+app.getSecret()+"---"+code);
		//构建get请求的url
		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="
					+appid
					+"&secret="
					+app.getSecret()+"&code="
					+code+"&grant_type=authorization_code";
		System.out.println(url);		
		String str=HttpClientUtil.sendGet(url);
		//判断拿到了返回值
		if(str!=null){
			//System.out.println(str);
			JSONObject json=JSONObject.fromObject(str);
			System.out.println(json);
			String openId=(String) json.get("openid");
			//判断返回值是成功状态
			if(openId!=null){
				//throw new SelfException("获取隐示授权的用户的openId失败");
				/*
				 * 根据channelId查询渠道表,获取该渠道的账号(phone)
				 */
				Channel chl=
						channelDao.getChannelByChannelId(Integer.parseInt(channelId));
				if(chl==null){
					throw new SelfException("该渠道不存在");
				}
				/*
				 * 根据获取隐示授权的用户的openId和channel_id 新增到临时用户表
				 * 		1、先查询openId和appId是否已经存在、存在那么不新增
				 * 		2、如果不存在直接新增
				 */
				tempUser.setOpenId(openId);
				tempUser.setAppId(appid);
				tempUser.setChannelId(Integer.parseInt(channelId));
				tempUser.setPhone(chl.getPhone());
				tempUser.setAppName(app.getAppName());
				tempUser.setCreateDate(new Date());
				
				System.err.println(tempUser);
				/*
				 * 查询临时用户表中是否已经存在openId和appid(同时存在、联合主键)
				 * 		如果不存在、那么新增、如果已经存在直接转发到页面
				 */
				Integer count=tempUserDao.getCount(openId, appid);
				if(count==0){
					boolean b=tempUserDao.addTempUser(tempUser);
					if(!b){
						throw new SelfException("新增渠道用户关系失败");
					}
				}
			}
		}
		
		try {
			//转发到页面
			resp.sendRedirect("http://ccsd.boya1.cn/link.html");
			return ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 获取access_token
	 */
	@Override
	public String getAccessToken(App app) {
		//1、构建get请求的url
		String url=
				"https://api.weixin.qq.com/cgi-bin/token?grant_type="+gt+"&appid="+app.getAppId()+"&secret="+app.getSecret();
		//2、发送get请求，获取String类型json对象
		String str=HttpClientUtil.sendGet(url);
		//3、解析数据      ---String类型的json转为JSONObject 
		JSONObject json=JSONObject.fromObject(str);
		System.err.println(json);
		String result=(String) json.get("access_token");
		if(result==null){
			throw new SelfException("获取AccessToken失败!");
		}
		/*
		 * 把生成的AccessToken放入单例中、通过判断时间、来判断是否过期(7200秒)
		 */
		AccessTokenSingle.getAccessToken().setToken(result);
		AccessTokenSingle.getAccessToken().setTempTime(System.currentTimeMillis());
		
		return result;
	}
	
	/* 
	 * 获取openId
	 * 根据access_token获取公众号的openId
	 */ 
	@Override
	public JSONArray getOpenIdArr(String accessToken){
		//获取access_token
		//String accessToken=getAccessToken(appid,secret);
		//构建get请求的url
		String url=
				"https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken;
		//发送get请求、获取String类型的json对象
		String str=HttpClientUtil.sendGet(url);
		//解析数据、获取openId数组
		//{"total": 57, "count": 57, "data": {"openid": ["oRbkdwupO7H4DQq9eCPPIfn9fJBE",...]} 
		JSONObject json=JSONObject.fromObject(str);
		System.err.println(json); 
		//先根据"data"转成String类型，再转为JSONObject格式、再根据"openid"得到数组
		//JSONArray openIdArr=JSONObject.fromObject(json.get("data")).getJSONArray("openid");
		Integer total=(Integer) json.get("total");
		//如果返回的是错误信息码、或者是成功信息但是没有一个关注用户
		if(total==null || total==0){
			throw new SelfException("获取关注用户的openId失败");
			//return null; 
		}
		JSONObject data=(JSONObject)json.get("data");
		JSONArray openIdArr=data.getJSONArray("openid");
		
		/*
		 * 因为微信接口中一次最多只能读取10000条数据、
		 * 	因此如果数据超过10000W条的数据、需要根据next_openid去获取
		 * 		num:读取次数	
		 */
		int consult=total/10000,remainder=total%10000,count=0;
		if(consult>0 && remainder==0){ //刚刚好整除 10000、20000。。。
			count=consult-1;
		}
		if(consult>0 && remainder>0){ //无法整除  10050/25000。。。
			count=consult;
		}
		for(int i=0;i<count;i++){
			System.out.println(openIdArr.get(count*10000-1));
			String url2=
					"https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken+"&next_openid="+openIdArr.get(count*10000-1);
			String st=HttpClientUtil.sendGet(url2);
			JSONObject json2=JSONObject.fromObject(st);
			JSONObject data2=(JSONObject)json.get("data");
			//判断返回成功、或者失败(不存在total为0的情况)
			if(data2==null){
				throw new SelfException("获取关注用户的openId失败");
			}
			JSONArray openIdArr1=data.getJSONArray("openid");
			for(int k=0;k<openIdArr1.size();k++){
				String openId1=(String) openIdArr1.get(k);
				openIdArr.add(openId1);
			}
		}
		
		return openIdArr;
	}
	/*
	 * 更新用户表
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public StatusMsg update(App app,String accessToken,JSONArray openIdArr){
		String appid=app.getAppId();
		/* 
		 * 1、遍历用户Id数组   获取openId
		 * 		1、查询用户表中是否存在数据      
		 * 			1、用户表中存在数据：
		 * 				1、更新用户状态为 1 取消关注   (用户表中存在某个用户、但是openId数组中不存在、已经取消关注)
		 * 				2、根据用户openId和appid查询用户表
		 * 					1、用户表中存在该用户   直接更新用户状态(根据id来更新)    continue
		 * 		2、1、用户表不存在数据   2、用户表存在数据、但是该用户数据不在用户表中 (新增用户status都设置0 关注)
		 * 			1、获取用户基本信息---得到用户关注公众号的时间
		 * 			2、根据openId和appid查询临时用户表  
		 * 				1、不存在  那么是自然量  channelId 设 0
		 * 				2、存在  比较关注时间和创建时间、判断是渠道导入还是自然量	
		 * 					1、关注时间早	自然量    	0
		 * 					2、创建时间早	渠道导入	渠道Id
		 */
		//查看用户表中数据量
		Integer num=userDao.getNum();
		if(num!=0){
			//更新用户状态为：取消关注 1   (整体更新、只更新一次)
			boolean b=userDao.updateStatus(1);
			if(!b){  
				return StatusMsg.failure();
				//throw new CommonException("整体更新用户状态失败");
			}
		}
		for(int i=0;i<openIdArr.size();i++){
			String openId=openIdArr.getString(i);
			//查询临时用户表、判断该用户的导入方式：渠道导入、还是自然量
			TempUser tempUser1=tempUserDao.getTempUser(openId, appid);
			if(num!=0){
				//查询用户表
				User user1=userDao.getUser(openId, appid);
				/*
				 * 如果用户表存在该用户、那么直接修改状态为  0 关注
				 * 	  注意：如果是已经在用户表中存在了的数据、那么说明是取消关注状态、
				 * 那么只要判断是否在临时用户表中存在即可、不存在那么是  自然量      
				 * 存在那么就是渠道导入(这里未对关注时间和点击推广链接的时间做比较、因为这种情况较少)
				 */
				if(user1!=null){
					if(tempUser1==null){
						boolean bl=userDao.updateStatusByopenId(openId, appid,0,null);
						//(添加队列、对更新情况进行处理、不在主线程进行处理)
						continue ;
					}
					/*
					 * 更新状态   只要关注时间比点击推广链接时间晚、就算
					 */
					boolean bl=userDao.updateStatusByopenId(openId, appid,0,tempUser1.getChannelId());
					//(添加队列、对更新情况进行处理、不在主线程进行处理)
					continue ;
				}
			}
			
			//1、用户表不存在数据   2、用户表存在数据、但是该用户数据不在用户表中
			//获取用户信息
			String url1=
					"https://api.weixin.qq.com/cgi-bin/user/info?access_token="
					+accessToken+"&openid="
					+openId+"&lang=zh_CN";
			/*
			 * 	如果需要获取用户的基本信息、那么需要进行转码
			 *	String userStr=new String(HttpClientUtil.sendGet(url1).getBytes("ISO-8859-1"),"UTF-8");
			*/
			String userStr=HttpClientUtil.sendGet(url1);
//			//如果返回值为空、跳过该openId
//			if(userStr==null){
//				continue;
//			}
			JSONObject userJson=JSONObject.fromObject(userStr);
			Integer time=(Integer) userJson.get("subscribe_time");
			//如果返回状态不是成功----那么获取不到subscribe_time、直接跳过该openId
			if(time==null){
				continue;
			}
			//等于null、不存在、自然量、直接新增
			if(tempUser1==null){
				user.setChannelId(0);
				user.setPhone("15111336587");
			}else{
				//存在、比较关注时间  、把时间都转成时间戳进行比较
				long createTime=tempUser1.getCreateDate().getTime();
				long showTime=time*1000L;
				if(showTime<createTime){ //先关注、后点击推广链接  自然量
					user.setChannelId(0);
					user.setPhone("15111336587");
				}else{  //先点击推广链接、后关注  渠道导入
					user.setChannelId(tempUser1.getChannelId());
					user.setPhone(tempUser1.getPhone());
				}
			}
			//生成user对象
			user.setAppId(appid);
			user.setOpenId(openId);
			user.setStatus(0); 
			user.setAppName(app.getAppName());
			user.setShowDate(new Date(time*1000L));
			user.setUpdateDate(new Date());
			//新增
			boolean b2=userDao.addUser(user);
			//添加队列、对新增结果进行处理、不在主线程处理
//			if(!b2){
//				throw new CommonException("新增失败");
//			}
		}
		
		return StatusMsg.success();
	}
	
	/*
	 * 发送消息
	 */
	@Override
	public StatusMsg sendInfo(String accessToken,JSONArray openIdArr,Info info,String appId) {
		//构建post请求的url
		String url=
				"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken;
		//构建要发送的json数据
		JSONObject json=new JSONObject();
		JSONObject news =new JSONObject();
		JSONArray articles=new JSONArray();
		JSONObject text=new JSONObject();
		
		//图文
		if(info.getType()==0){
		//article 里添加数据
		JSONObject article=new JSONObject();
		article.put("title", info.getTitle());
		article.put("description", info.getDescription());
		article.put("url", info.getsUrl());
		article.put("picurl", info.getpUrl());
		articles.add(article);
		//把article数组添加到news中
		news.put("articles", articles);
		}else if(info.getType()==1){
			//文本
			text.put("content", info.getDescription());
		}
		
		for(int i=0;i<openIdArr.size();i++){
			String openId=openIdArr.getString(i);
			json.put("touser", openId);
			
			if(info.getType()==0){
			json.put("msgtype", "news");
			//把news添加到json中
			json.put("news", news);
			}else if(info.getType()==1){
				json.put("msgtype", "text");
				json.put("text", text);
			}
			
			//if("oRbkdwh8_Nrg6SkLDmJA3bURohrw".equals(openId)
			//		/*||"oRbkdwlukzHf5yLtxjLF_Ujw3i84".equals(openId)*/){
				String str=HttpClientUtil.sendPost(url, json);
				//解析返回数据      根据返回信息判断1、如果推送信息失败那么抛异常   2、如果成功那么更新数据表
				//{"errcode":0,"errmsg":"ok"}
				JSONObject obj=JSONObject.fromObject(str);
				System.out.println(obj);
				Integer code=(Integer)obj.get("errcode");
				System.out.println(code);
				if(code!=0){ 
					if(code==45015){
						//更新用户表 把该用户状态设置为2 关注不活跃
						boolean bl=userDao.updateStatusByopenId(openId, appId, 2, null);
						//添加队列进行处理
//						if(!bl){
//							throw new SelfException("更新用户状态为2失败!");
//						}
					}else{
						//return StatusMsg.backMsg(40000, "推送失败!");
						//throw new SelfException("推送消息失败!");
					}
				}
		//	}
		}
		
		return StatusMsg.success();
	}
	
	
}
