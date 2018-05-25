package com.dlg.wxsend.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/*
 * 用户
 */
@Component
public class User implements Serializable{

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
	 * 用户状态   0 关注  1  取消关注   2  关注不活跃
	 */
	private Integer status;
	/*
	 * 关注时间(用户关注公众号的时间)
	 */
	private Date showDate;
	/*
	 * 更新时间(更新数据的时间)
	 */
	private Date updateDate;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "User [openId=" + openId + ", appId=" + appId + ", channelId=" + channelId + ", phone=" + phone
				+ ", appName=" + appName + ", status=" + status + ", showDate=" + showDate + ", updateDate="
				+ updateDate + "]";
	}
	
}
