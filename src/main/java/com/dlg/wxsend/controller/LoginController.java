package com.dlg.wxsend.controller;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
/*
 * 登录模块
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dlg.wxsend.bean.Account;
import com.dlg.wxsend.service.LoginService;
import com.dlg.wxsend.util.StatusMsg;


@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	/*
	 * 获取验证码
	 */
	@RequestMapping(value="/code",method=RequestMethod.GET)
	@ResponseBody
	public void getCode(HttpSession session,HttpServletResponse resp){
		loginService.getCode(session, resp);
		String code=(String) session.getAttribute("checkCode");
		System.err.println(code);
	}
	
	/*
	 * 登录验证
	 */
	@RequestMapping(value="/",method=RequestMethod.POST)
	@ResponseBody
	public StatusMsg login(
			@RequestParam("phone") String phone,
			@RequestParam("password") String password,
			@RequestParam("code") String code,
			@RequestParam("role") String role,
			HttpSession session){
		StatusMsg statusMsg=loginService.login(phone, password, code, role, session);
		
		return statusMsg;
	}
	
	/*
	 * 是否已经登录验证
	 */
	@RequestMapping(value="/isLogin",method=RequestMethod.GET)
	@ResponseBody
	public StatusMsg isLogin(HttpSession session){
		Account account=(Account) session.getAttribute("Account");
		if(account==null){
			return StatusMsg.failure();
		}
		return StatusMsg.success();
	}
	
	/*
	 * 退出登录
	 */
	@RequestMapping(value="/exit",method=RequestMethod.GET)
	@ResponseBody
	public StatusMsg exitLogin(HttpSession session){
		session.removeAttribute("Account");
		return StatusMsg.success();
	}
	
}
