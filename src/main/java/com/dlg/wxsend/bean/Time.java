package com.dlg.wxsend.bean;

import java.io.Serializable;
import java.util.Date;

/*
 * 发送时间
 */
public class Time implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * 发送时间Id
	 */
	private Integer tId;
	/*
	 * 发送类型  0 固定时间发送  1 自定义时间发送
	 */
	private Integer type;
	/*
	 * 固定发送时间
	 */
	private Date suTime;
	/*
	 * 自定义发送时间
	 */
	private String slTime;
	/*
	 * 发送状态  0 未发送  1已发送
	 */
	private Integer status;
	/*
	 * 发送信息Id
	 */
	private Integer sId;
	/*
	 * 账号Id
	 */
	private Integer accountId;
	/*
	 * 账号名称
	 */
	private String accountName;
	/*
	 * 创建时间
	 */
	private Date createDate;
	public Integer gettId() {
		return tId;
	}
	public void settId(Integer tId) {
		this.tId = tId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getSuTime() {
		return suTime;
	}
	public void setSuTime(Date suTime) {
		this.suTime = suTime;
	}
	public String getSlTime() {
		return slTime;
	}
	public void setSlTime(String slTime) {
		this.slTime = slTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getsId() {
		return sId;
	}
	public void setsId(Integer sId) {
		this.sId = sId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Time [tId=" + tId + ", type=" + type + ", suTime=" + suTime + ", slTime=" + slTime + ", status="
				+ status + ", sId=" + sId + ", accountId=" + accountId + ", accountName=" + accountName
				+ ", createDate=" + createDate + "]";
	}
	
}
