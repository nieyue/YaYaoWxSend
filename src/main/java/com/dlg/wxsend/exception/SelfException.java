package com.dlg.wxsend.exception;

import java.io.Serializable;

/*
 * 自定义异常：
 * 		直接返回接收的异常信息 和异常状态码 40001
 */
public class SelfException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer errCode;
	private String errMsg;
	
	/*
	 * 接收异常信息
	 */
	public SelfException(String errMsg) {
		super(errMsg);
		this.errMsg = errMsg;
	}
	public Integer getErrCode() {
		return errCode;
	}
	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
