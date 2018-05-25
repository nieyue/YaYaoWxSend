package com.dlg.wxsend.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WxUtil {
	/*
	 * 获取微信用户信息中、用户关注时间、并转化
	 */
	public static String  getTextDate(Integer time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String s=sdf.format(new Date(time*1000L));
		//System.out.println(s);
		return s;
	}
	
	/*
	 * 把文本格式转换为日期
	 */
	public static Date getDate(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date d=null;
		try {
			d = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
}
