package com.dlg.wxsend.service;

import org.springframework.web.multipart.MultipartFile;

import com.dlg.wxsend.bean.Info;
import com.dlg.wxsend.util.StatusMsg;
import com.github.pagehelper.PageInfo;

/*
 * 发送信息
 */
public interface InfoService {
	/*
	 * 图片上传
	 */
	public StatusMsg upload(MultipartFile file);
	
	/*
	 * 新增发送信息
	 */
	public StatusMsg addInfo(Info info);
	
	/*
	 * 分页查询信息
	 */
	public PageInfo getInfo(Integer pageNum,Integer pageSize);

	/*
	 * 修改发送信息
	 */
	public StatusMsg updateInfo(Info info);
	
}
