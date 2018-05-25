package com.dlg.wxsend.bean;

import java.io.Serializable;
import java.util.Date;

/*
 * 用户详情
 */
public class UserDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * 用户详情Id
	 */
	private Integer udId;
	/*
	 * 用户Id
	 */
	private String openId;
	/*
	 * 用户昵称
	 */
	private String nickName;
	/*
	 * 性别(0   女   1 男)
	 */
	private Integer sex;
	/*
	 * 语言
	 */
	private String language;
	/*
	 * 城市
	 */
	private String city;
	/*
	 * 省份
	 */
	private String province;
	/*
	 * 国家
	 */
	private String country;
	/*
	 * 头像
	 */
	private String headImgUrl;
	/*
	 * 关注时间
	 */
	private Date subscribeTime;
	/*
	 * 创建时间
	 */
	private Date createDate;
	public Integer getUdId() {
		return udId;
	}
	public void setUdId(Integer udId) {
		this.udId = udId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public Date getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "UserDetails [udId=" + udId + ", openId=" + openId + ", nickName=" + nickName + ", sex=" + sex
				+ ", language=" + language + ", city=" + city + ", province=" + province + ", country=" + country
				+ ", headImgUrl=" + headImgUrl + ", subscribeTime=" + subscribeTime + ", createDate=" + createDate
				+ "]";
	}
	
}
