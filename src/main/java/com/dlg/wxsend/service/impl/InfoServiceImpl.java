package com.dlg.wxsend.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dlg.wxsend.bean.Info;
import com.dlg.wxsend.dao.InfoDao;
import com.dlg.wxsend.service.InfoService;
import com.dlg.wxsend.util.StatusMsg;
import com.dlg.wxsend.util.UploadUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/*
 * 发送信息
 */
@Service
public class InfoServiceImpl implements InfoService {
	@Value("${location.picType}")
	private String picType;
	
	@Value("${location.picSize}")
	private Integer picSize;
	
	@Value("${web.upload-path}")
	private String path;
	
	@Value("${web.upload-url}")
	private String url;
	
	@Autowired
	private InfoDao infoDao;
	
	/*
	 * 图片上传
	 */
	@Override
	public StatusMsg upload(MultipartFile file) {
		System.out.println(picType+"---"+picSize+"---"+path+"---"+url);
		/*
		 * 1、判空
		 */
		boolean isEmpty=UploadUtil.isEmpty(file);
		if(!isEmpty){
			return StatusMsg.backMsg(40000, "请上传图片");
		}
		/*
		 * 2、判断图片格式
		 */
		boolean type=UploadUtil.picType(file, picType);
		if(!type){
			return StatusMsg.backMsg(40000, "图片格式有误");
		}
		/*
		 * 3、判断图片大小
		 */
		boolean size=UploadUtil.picSize(file,picSize);
		if(!size){
			return StatusMsg.backMsg(40000, "图片大小不能超过300KB");
		}
		/*
		 * 4、生成图片名字
		 */
		String picName=UploadUtil.createName(file);
		/*
		 * 5、上传到图片服务器
		 */
		File f=new File(path+picName);
		try {
			file.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * 6、生成返回给页面的URL
		 */
		String picUrl=url+picName;
		System.out.println(picUrl);
		
		return StatusMsg.success().add("msg", picUrl);
	}
	
	/*
	 * 新增发送信息
	 */
	@Override
	public StatusMsg addInfo(Info info) {
		if(info.getType()==null){
			info.setType(0);			
		}
		info.setCreateDate(new Date());
		boolean b=infoDao.addInfo(info);
		if(!b){
			return StatusMsg.failure();
		}
		return StatusMsg.success();
	}
	
	/*
	 * 分页查询信息
	 */
	@Override
	public PageInfo getInfo(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Info> list=infoDao.getInfo();
		PageInfo info=new PageInfo(list);
		for(Info ino: list){
			System.out.println(ino);
		}
		
		return info;
	}

	@Override
	public StatusMsg updateInfo(Info info) {
		boolean b=infoDao.updateInfo(info);
		if(!b){
			return StatusMsg.failure();
		}
		return StatusMsg.success();
	}

}
