package com.dlg.wxsend.util;

import java.util.HashMap;
import java.util.Map;

/*
 * 封装返回信息类:
 * 1、状态码 : code
 * 		成功：200	  一般(常用)失败：40000       自定义：40001	页面输入提示：40002
 * 2、信息 : msg
 * 3、需要返回的数据 : Map
 */
public class StatusMsg {
	/*
	 * 状态码 
	 */
	private int code;
	/*
	 * 返回信息
	 */
	private String msg;
	/*
	 * 返回给浏览器的数据
	 */
	Map<String,Object> info=new HashMap<String,Object>();
	
	/*
	 * 封装成功状态的部分Msg信息，并返回一个静态的Msg对象，可以直接调用并设置返回数据信息
	 */
	public static StatusMsg success(){
		StatusMsg result=new StatusMsg();
		result.setCode(200);
		result.setMsg("Success!");
		return result;
	}
	
	/*
	 * 封装失败状态的部分Msg信息，并返回一个静态的Msg对象，可以直接调用并设置返回数据信息
	 */
	public static StatusMsg failure(){
		StatusMsg result=new StatusMsg();
		result.setCode(40000);
		result.setMsg("Fail!");
		return result;
	}
	
	/*
	 * 自定义返回状态码和返回信息
	 */
	public static StatusMsg backMsg(Integer code,String msg){
		StatusMsg result=new StatusMsg();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	/*
	 * 封装返回数据
	 */
	public StatusMsg add(String str,Object obj){
		this.getInfo().put(str, obj);
		return this;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getInfo() {
		return info;
	}
	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}
	
	
}
