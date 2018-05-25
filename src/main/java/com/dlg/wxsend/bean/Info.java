package com.dlg.wxsend.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/*
 * 发送信息
 */
@Component
public class Info implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * 信息Id
	 */
	private Integer sId;
	/*
	 * 信息类型 0  图文类型news,1文本消息text
	 */
	private Integer type;
	/*
	 * 信息标题
	 */
	private String title;
	/*
	 * 描述
	 */
	private String description;
	/*
	 * 跳转路径
	 */
	private String sUrl;
	/*
	 * 图片路径
	 */
	private String pUrl;
	/*
	 * 创建时间
	 */
	private Date createDate;
	public Integer getsId() {
		return sId;
	}
	public void setsId(Integer sId) {
		this.sId = sId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getsUrl() {
		return sUrl;
	}
	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}
	public String getpUrl() {
		return pUrl;
	}
	public void setpUrl(String pUrl) {
		this.pUrl = pUrl;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Info [sId=" + sId + ", type=" + type + ", title=" + title + ", description=" + description + ", sUrl="
				+ sUrl + ", pUrl=" + pUrl + ", createDate=" + createDate + "]";
	}
	
}
