package com.dlg.wxsend.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传工具类：封装了以下几种方法
 * 1、图片格式
 * 2、图片大小
 * 3、图片名称随机数
 * @author dlg
 *
 */
public class UploadUtil {
		//判空
		public static boolean isEmpty(MultipartFile file){
			if(file!=null){
				if(file.getSize()>0){
					return true;
				}
			}
			return false; 
		}

		//1、图片格式  如果返回false则表示图片格式错误  
		public static boolean picType(MultipartFile file,String picType){
			String originalName=file.getOriginalFilename();
			String backName=originalName.substring(originalName.lastIndexOf(".")+1);
			String[] names=picType.split(",");
			for(int i=0;i<names.length;i++){
				if(backName.equals(names[i])){
					return true;
				}
			}
			return false;
		}
		
		//2、图片大小   不能超过300kB  如果返回false则表示图片大小超过300kB
		public static boolean picSize(MultipartFile file,long picSize){
			long size=file.getSize();
			//页面传来的图片大小是B   需要转换为MB	Math.round(imgSize/1024*100/1024) 小于1MB
			if(Math.round(size/1024)>picSize){//B 转为KB
				return false;
			}
			return true;
		}
		
		//3、图片名字随机数    当前时间的年月日，时分秒+一个三位随机数
		public static String createName(MultipartFile file){
			String originalName=file.getOriginalFilename();
			String backName=originalName.substring(originalName.lastIndexOf("."));
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			String str=sdf.format(d);
			long math=Math.round(Math.random()*1000);
			String picName=str+math+backName;
			return picName;
		}
		
}
