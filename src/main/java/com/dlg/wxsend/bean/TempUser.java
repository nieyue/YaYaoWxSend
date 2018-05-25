package com.dlg.wxsend.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/*
 * 临时用户
 */
@Component
public class TempUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * 用户Id
	 */
	private String openId;
	/*
	 * 开发者Id
	 */
	private String appId;
	/*
	 * 渠道Id
	 */
	private Integer channelId;
	/*
	 * 渠道账号
	 */
	private String phone;
	/*
	 * 公众号名称
	 */
	private String appName;
	/*
	 * 创建时间
	 */
	private Date createDate;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "TempUser [openId=" + openId + ", appId=" + appId + ", channelId=" + channelId + ", phone=" + phone
				+ ", appName=" + appName + ", createDate=" + createDate + "]";
	}
	
}
