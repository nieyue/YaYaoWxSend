package com.dlg.wxsend.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dlg.wxsend.util.StatusMsg;

/*
 * 统一异常处理：
 * 	 1、 捕获抛到controller层的异常
 * 	 2、并返回给前端显示
 */
@RestControllerAdvice
public class AllExceptionCatch {
	/*
	 * 一般异常
	 */
	@ExceptionHandler(value=CommonException.class)
	@ResponseBody
	public StatusMsg acountIsExistErrorHandler(Exception e) throws Exception {
		return StatusMsg.failure();
	}
	
	/*
	 * 自定义异常类
	 */
	@ExceptionHandler(value=SelfException.class)
	@ResponseBody
	public StatusMsg codeException(SelfException se){
		se.setErrCode(40001);
		//sce.setErrMsg("获取验证码失败!");
		return StatusMsg.backMsg(se.getErrCode(),se.getErrMsg());
	}
	
	
}
