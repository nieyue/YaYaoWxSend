package com.dlg.wxsend.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/*
 * 公众号
 */
@Component
public class App implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * 公众号Id
	 */
	private Integer pubAppId;
	/*
	 * 开发者Id
	 */
	private String appId;
	/*
	 * 开发者密码
	 */
	private String secret;
	/*
	 * 公众号名称
	 */
	private String appName;
	/*
	 * 公众号状态    0  正常   1  异常
	 */
	private Integer status;
	/*
	 * 创建时间
	 */
	private Date createDate;
	public Integer getPubAppId() {
		return pubAppId;
	}
	public void setPubAppId(Integer pubAppId) {
		this.pubAppId = pubAppId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "App [pubAppId=" + pubAppId + ", appId=" + appId + ", secret=" + secret + ", appName=" + appName
				+ ", status=" + status + ", createDate=" + createDate + "]";
	}
	
	
}	
