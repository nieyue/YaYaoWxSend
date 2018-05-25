package com.dlg.wxsend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dlg.wxsend.bean.TempUser;

/*
 * 临时用户表
 */
@Mapper
public interface TempUserDao {
	/*
	 * 新增
	 */
	public boolean addTempUser(@Param("tempUser") TempUser tempUser);
	
	/*
	 * 根据openId和appid查询数量(联合主键、去重)
	 */
	public Integer getCount(@Param("openId")String openId,@Param("appid")String appid);

	/*
	 * 根据openId和appid查询(联合主键)
	 */
	public TempUser getTempUser(@Param("openId")String openId,@Param("appid")String appid);

}
