package com.dlg.wxsend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dlg.wxsend.bean.Info;
import com.dlg.wxsend.service.InfoService;
import com.dlg.wxsend.util.StatusMsg;
import com.github.pagehelper.PageInfo;

/*
 * 发送信息
 */
@RestController
@RequestMapping("/info")
public class InfoController {
	@Autowired
	private InfoService infoService;
	
	/*
	 * 图片上传
	 */
	@RequestMapping(value="/upload",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public StatusMsg upload(@RequestParam(value="picFile",required=false,defaultValue="null")MultipartFile file){
		//System.out.println(file);
		StatusMsg msg=infoService.upload(file);
		
		return msg;
	}
	
	/*
	 * 新增信息
	 */
	@RequestMapping(value="/send")
	@ResponseBody
	public StatusMsg addInfo(Info info){
		System.out.println(info);
		StatusMsg msg=infoService.addInfo(info);
		return msg;
	}
	/*
	 * 新增信息
	 */
	@RequestMapping(value="/update")
	@ResponseBody
	public StatusMsg updateInfo(Info info){
		StatusMsg msg=infoService.updateInfo(info);
		return msg;
	}
	
	/*
	 * 分页查询信息
	 */
	@RequestMapping(value="/select")
	@ResponseBody
	public PageInfo getInfo(
			@RequestParam("num")Integer pageNum,
			@RequestParam("size")Integer pageSize){
		System.out.println(pageNum+"----"+pageSize);
		PageInfo pi=infoService.getInfo(pageNum, pageSize);
		
		return pi;
	}
	
	
	
}
