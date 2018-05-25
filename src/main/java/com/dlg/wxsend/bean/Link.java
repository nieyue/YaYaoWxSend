package com.dlg.wxsend.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/*
 * 推广链接
 */
@Component
public class Link implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 渠道名称
	 */
	private String channelName;
	/*
	 * 公众号名称
	 */
	private String appName;
	/*
	 * 链接
	 */
	private String link;
	
	public Link() {
		super();
	}

	public Link(String channelName, String appName, String link) {
		super();
		this.channelName = channelName;
		this.appName = appName;
		this.link = link;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Link [channelName=" + channelName + ", appName=" + appName + ", link=" + link + "]";
	}
	
}
