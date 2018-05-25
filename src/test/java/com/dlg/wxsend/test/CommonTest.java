package com.dlg.wxsend.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.dlg.wxsend.util.AccessTokenSingle;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CommonTest {
	public static void main(String[] args) throws ParseException {
		long tempTime=0;
		long nowTime=System.currentTimeMillis();
		if(tempTime!=0 && (nowTime-tempTime)/1000>3600){
			System.out.println("fdsda");
			
		}
		System.out.println("dsfsd");
		
//		Date d=new Date();
//		System.out.println(d.getTime());
//		long l=System.currentTimeMillis();
//		System.out.println(l);
		
//		AccessTokenSingle accessToken1=AccessTokenSingle.getAccessToken();
//		accessToken1.setToken("12345");
//		accessToken1.setDate(new Date());
//		System.out.println(accessToken1.getToken());
//		AccessTokenSingle accessToken2=AccessTokenSingle.getAccessToken();
//		accessToken2.setToken("sdfa");
//		System.out.println(accessToken2.getToken());
//		System.out.println(accessToken1.getToken());
		
		//com.dlg.wxsend.util.AccessTokenSingle@15db9742
		
		
		
//		JSONObject json=new JSONObject();
//		JSONObject news=new JSONObject();
//		JSONObject article=new JSONObject();
//		
//		
//		//json2 里添加数据
//		article.put("title", "天下第一");
//		article.put("description", "雄姿英发");
//		article.put("url", "http://www.baidu.com");
//		article.put("picurl", "http://www.itea1.cn/1.png");
//
//		//把json2数组添加到json1中
//		news.put("articles", article);
//		
//		String[] openIdArray={"wx4d2cb896c1256cbe","fsdfsdfsdfsd","21312dasdas"};
//		for(int i=0;i<openIdArray.length;i++){
//			json.put("touser", openIdArray[i]);
//			json.put("msgtype", "news");
//			//把json1添加到json中
//			json.put("news", news);
//			System.out.println(json);
//		}
//		
//		System.out.println(json+"\n"+news+"\n"+article);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//		String s=sdf.format(new Date(1492694957*1000L));
//		Date d=sdf.parse(s);
//		System.out.println(s);
		//1482694957000  
//		System.out.println(new Date(1482694957000L));
		
//		String arr="['sdfsdfsd','sfsdsdfsdfsfsdf','sdfsdsddfsdf']";
//		String arr2="['23423','3423423']";
//		JSONArray obj=JSONArray.fromObject(arr);
//		JSONArray obj2=JSONArray.fromObject(arr2);
//		
//		System.out.println(obj+"---"+obj2);
//		for(Object str: obj2){
//			obj.add(str);
//		}
//		System.out.println(obj);
		
//		int[] arr3 = new int[]{20,21,22};
//		JSONArray jarr=new JSONArray();
	
		
		
//		obj.addAll(Arrays.asList(obj2));
//		System.out.println(obj);

//		String arr2="[{name:'1',value:'2'},{n:'25',v:'12'}]";
//		JSONArray obj2=JSONArray.fromObject(arr2);
//		System.out.println(obj2);
		
		//System.out.println(Arrays.asList(obj));
		//obj.addAll(Arrays.asList(obj2));
		//System.out.println(obj);
		
	}
}	

