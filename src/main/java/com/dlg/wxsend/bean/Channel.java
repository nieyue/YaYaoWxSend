package com.dlg.wxsend.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/*
 * 渠道
 */
@Component
public class Channel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * 渠道Id
	 */
	private Integer channelId;
	/*
	 * 渠道名称
	 */
	private String name;
	/*
	 * 渠道账号
	 */
	private String phone;
	/*
	 * 创建时间
	 */
	private Date createDate;
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Channel [channelId=" + channelId + ", name=" + name + ", phone=" + phone + ", createDate=" + createDate
				+ "]";
	}
	
}
