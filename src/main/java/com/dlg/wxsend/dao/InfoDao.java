package com.dlg.wxsend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dlg.wxsend.bean.Info;

/*
 * 发送信息
 */
@Mapper
public interface InfoDao {
	/*
	 * 新增发送信息
	 */
	public boolean addInfo(@Param("info") Info info);
	
	/*
	 * 查询所有信息
	 */
	public List<Info> getInfo();

	/*
	 * 修改发送信息
	 */
	public boolean updateInfo(@Param("info")Info info);
	
	
}
