package com.dlg.wxsend.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UrlEncodeTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
//		String url="http://www.baidu.com";
//		//URLencode 编码
//		String encode=URLEncoder.encode(url,"UTF-8");
//		//解码
//		String decode=URLDecoder.decode(encode, "UTF-8");
//		System.out.println(encode+"---"+decode);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String s=format.format(new Date(1499703686*1000L));
		System.out.println(s);
		
		
		int d=0/10000;
		int i=0%10000;
		System.out.println(d+"----"+i);
		int a=0;
		if(d>0&&i==0){
			a=d-1;
		}
		if(d>0&&i>0){
			a=d; 
		}
		for(int j=0;j<=a;j++){
			System.out.println("fasd");
		}
	}
}
