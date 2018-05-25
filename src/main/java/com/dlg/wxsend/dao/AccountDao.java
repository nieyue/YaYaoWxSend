package com.dlg.wxsend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dlg.wxsend.bean.Account;

/*
 * 对账户表操作
 */
//@Repository    注：SpringBoot 扫描不到@Repository、因此无法生成bean对象
@Mapper
public interface AccountDao {
	/*
	 * 根据账号(phone)查询Account
	 */
	public Account getAccountByPhone(String phone);
	
	/*
	 * 新增(渠道)账号
	 */
	public boolean addAccount(@Param("account") Account account);
}
