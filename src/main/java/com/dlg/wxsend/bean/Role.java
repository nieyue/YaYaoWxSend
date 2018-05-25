package com.dlg.wxsend.bean;
/*
 * 角色
 */
import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * 角色Id
	 */
	private Integer roleId;
	/*
	 * 角色名称
	 */
	private String roleName;
	/*
	 * 创建时间
	 */
	private Date createDate;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", createDate=" + createDate + "]";
	}
	
}
