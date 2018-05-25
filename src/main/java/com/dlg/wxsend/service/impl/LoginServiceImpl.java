package com.dlg.wxsend.service.impl;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
/*
 * 登录模块
 */
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dlg.wxsend.bean.Account;
import com.dlg.wxsend.dao.AccountDao;
import com.dlg.wxsend.exception.SelfException;
import com.dlg.wxsend.service.LoginService;
import com.dlg.wxsend.util.ImgCheckCodeUtil;
import com.dlg.wxsend.util.MD5Code;
import com.dlg.wxsend.util.StatusMsg;


@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private AccountDao accountDao;
	
	/*
	 * 获取验证码
	 */
	@Override
	public void getCode(HttpSession session, HttpServletResponse resp) {
		//1、获取图片验证码数组
		Object[] obj=ImgCheckCodeUtil.createImage();
		//2、把验证码存放在session中
		session.setAttribute("checkCode", obj[0].toString());
		//3、获取生成的图片、存入图片流缓冲区BufferedImage
		BufferedImage img=(BufferedImage) obj[1];
		//4、通过图片流写入resp的输出流，输出到前端页面
		OutputStream out;
		try {
			out = resp.getOutputStream();
			ImageIO.write(img, "png", out);
		} catch (IOException e) {
			throw new SelfException("验证码获取失败!");
		}
		
	}
	
	/*
	 * 登录验证
	 */
	@Override
	public StatusMsg login(String phone, String password, String code, String role, HttpSession session) {
		//1、非空验证
		if(StringUtils.isEmpty(phone.trim())){
			return StatusMsg.backMsg(40002, "请输入用户名");
		}
		if(StringUtils.isEmpty(password.trim())){
			return StatusMsg.backMsg(40002, "请输入密码");
		}
		if(StringUtils.isEmpty(code.trim())){
			return StatusMsg.backMsg(40002, "请输入验证码");
		}
		//2、验证验证码是否正确
		String checkCode=(String) session.getAttribute("checkCode");
		if(!checkCode.equalsIgnoreCase(code)){
			return StatusMsg.backMsg(40002, "验证输入错误");
		}
		/*
		 * 3、判断账户信息 
		 * 		对密码进行MD5加密
		 * 		1、根据账号(phone)查找是否存在该账户
		 * 		2、判断密码是否正确
		 * 		3、判断角色是否正确
		 * 		4、判断账户状态
		 * 		5、把账户信息存入session中
		 * 		6、把账户信息返回给页面
		 */
		String pass=MD5Code.getMD5(password);
		//1
		Account account=accountDao.getAccountByPhone(phone);
		if(account==null){
			return StatusMsg.backMsg(40000, "该账号不存在");
		}
		//2
		if(!pass.equals(account.getPassword())){
			return StatusMsg.backMsg(40000, "密码错误");
		}
		//3
		if(!role.equals(account.getRoleName())){
			return StatusMsg.backMsg(40000, "角色选择错误");
		}
		//4
		if(account.getStatus()==1){
			return StatusMsg.backMsg(40000, "该账户异常");
		}else if(account.getStatus()==2){
			return StatusMsg.backMsg(40000, "该账户已经被锁定");
		}
		//5
		session.setAttribute("Account", account);
		System.out.println(account);
		//6
		return StatusMsg.success().add("Account", account);
	}

}
