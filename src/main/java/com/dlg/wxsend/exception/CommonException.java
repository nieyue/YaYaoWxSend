package com.dlg.wxsend.exception;

import java.io.Serializable;

/*
 * 一般异常：
 * 		直接返回fail和异常状态码40000
 */
public class CommonException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public CommonException(String msg){
		super(msg);
	}
}
