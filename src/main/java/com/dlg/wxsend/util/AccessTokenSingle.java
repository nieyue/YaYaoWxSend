package com.dlg.wxsend.util;

import java.util.Date;

/*
 * AccessToken的单例
 *  1、token:获取请求微信生成的accessToken
 *  2、tempTime:记录每次请求时的系统时间
 *  	调用时通过比较时间差、判断accessToken是否过期
 */
public class AccessTokenSingle {
	private  String token;
	private  long tempTime;
	
	/*
	 * 静态内部类
	 * 	第一次初始化时加载
	 */
	private static class LazyHolder { 
		private static AccessTokenSingle accessToken = new AccessTokenSingle();
	    }  
	
	private AccessTokenSingle(){
		
	};
	
	public static  AccessTokenSingle getAccessToken(){
		return LazyHolder.accessToken;
	}
	
	public String getToken() {
		return token;
	}

	public  void setToken(String token) {
		this.token = token;
	}

	public long getTempTime() {
		return tempTime;
	}

	public void setTempTime(long tempTime) {
		this.tempTime = tempTime;
	}
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				AccessTokenSingle.getAccessToken().setToken((String)null);
				
			}
		});
		t1.start();
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				AccessTokenSingle.getAccessToken().setToken("23df");
				
			}
		});
		t2.start();
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(AccessTokenSingle.getAccessToken().getToken());
	}
	
}
