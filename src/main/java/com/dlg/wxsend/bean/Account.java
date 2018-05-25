package com.dlg.wxsend.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/*
 * 登录账号
 */
@Component
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * 账号Id
	 */
	private Integer accountId;
	/*
	 * 账号名称
	 */
	private String name;
	/*
	 * 账号(手机号码)
	 */
	private String phone;
	/*
	 * 密码
	 */
	private String password;
	/*
	 * 状态 0 正常   1  异常    2  锁定
	 */
	private Integer status;
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
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
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
		return "Account [accountId=" + accountId + ", name=" + name + ", phone=" + phone + ", password=" + password
				+ ", status=" + status + ", roleId=" + roleId + ", roleName=" + roleName + ", createDate=" + createDate
				+ "]";
	}
	
}
