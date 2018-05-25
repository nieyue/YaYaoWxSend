package com.dlg.wxsend.dao;

import org.apache.ibatis.annotations.Mapper;

import com.dlg.wxsend.bean.Role;


/*
 * 角色
 */
@Mapper
public interface RoleDao{
	/*
	 * 根据角色名、查询角色
	 */
	public Role getRole(String roleName);
	
}
