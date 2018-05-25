package com.dlg.wxsend.test;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dlg.wxsend.service.WeiXinService;

/*
 * Service测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
	@Autowired
	private WeiXinService wxService;
	
	@Test
	public void wxServiceTest(){
		//String accessToken=wxService.getAccessToken("wx4d2cb896c1256cbe", "5f3a5483468d2eec01e8fd4a8ce2b8cf");
		//System.out.println(accessToken);
		
		//wxService.getOpenIdArr("wx4d2cb896c1256cbe");
	}
}
