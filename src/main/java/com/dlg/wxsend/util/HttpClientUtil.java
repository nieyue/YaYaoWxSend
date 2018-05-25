package com.dlg.wxsend.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.dlg.wxsend.exception.SelfException;

import net.sf.json.JSONObject;


/*
 * 本类封装了：
 * 	通过HttpClient向服务器发送 POST、GET请求，获取返回数据
 */
public class HttpClientUtil {
	/*
	 * HttpClient  post
	 * 发送post请求
	 */
	public static String sendPost(String url,JSONObject json){
	    String result=null;
		//1、创建HttpClient对象
		CloseableHttpClient client = HttpClients.createDefault();
		//2、创建post请求方式
	    HttpPost post = new HttpPost(url);
	    //3、设置请求格式
        StringEntity s = new StringEntity(json.toString(),Charset.forName("UTF-8"));
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");//发送json数据需要设置contentType
        post.setEntity(s);
	    try {
	        //4、执行post请求、获取返回对象
	        HttpResponse res = client.execute(post);
	        //5、根据返回值判断
	        if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	        	//6、获取返回值
	            HttpEntity entity = res.getEntity();
	            result = EntityUtils.toString(entity);
	        }
	    } catch (Exception e) {
	      throw new SelfException("发送post请求失败!");
	    }
	    return result;
	  }
	
	/*
	 * HttpClient Get
	 * 发送get请求
	 */
	public static String sendGet(String url){  
        String result=null;
        CloseableHttpResponse response=null;
        //创建httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
            try {  
            	// 创建httpget.  
                HttpGet httpget = new HttpGet(url);  
                // 执行get请求.  
                response = httpclient.execute(httpget);  
                //System.err.println(response.getStatusLine().getStatusCode());      
                // 获取响应实体         
                HttpEntity entity = response.getEntity();  
                // 转换为String类型
                if (null!=entity) {  
                	result=EntityUtils.toString(entity);   //toString()方法中已经有关闭流的操作了
                }  
            } catch (IOException e) { 
            	e.printStackTrace();
            	throw new SelfException("发送get请求失败!");
        } 
        return result;
    }  
}
