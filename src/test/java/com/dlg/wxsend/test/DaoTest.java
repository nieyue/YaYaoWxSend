package com.dlg.wxsend.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dlg.wxsend.bean.App;
import com.dlg.wxsend.bean.TempUser;
import com.dlg.wxsend.dao.AccountDao;
import com.dlg.wxsend.dao.AppDao;
import com.dlg.wxsend.dao.ChannelDao;
import com.dlg.wxsend.dao.RoleDao;
import com.dlg.wxsend.dao.TempUserDao;
import com.dlg.wxsend.dao.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private ChannelDao channelDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private AppDao appDao;
	
	@Autowired
	private TempUser tempUser;
	
	@Autowired
	private TempUserDao tempUserDao;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void userDaoTest(){
//		boolean b=userDao.updateStatus(0);
//		System.out.println(b);
//		Integer count=userDao.getNum();
//		System.out.println(count);
		boolean b=
				userDao.updateStatusByopenId("oRbkdwgvKXtCII5D4iWMhpAkwkms", "wx4d2cb896c1256cbe", 0, null);
		System.out.println(b);
	}
	
	@Test
	public void accountDaoTest(){
		System.out.println(accountDao.getAccountByPhone("18774814879"));
	}
	
	@Test
	public void channelDaoTest(){
		Integer count=channelDao.getCountByPhone("111");
		System.out.println(count);
	}
	
	@Test
	public void roleDaoTest(){
		System.out.println(roleDao.getRole("渠道主"));
	}
	
	@Test
	public void appDaoTest(){
		App app=appDao.getAppByAppId("1");
		System.out.println(app);
	}
	
	@Test 
	public void tempUserTest(){
		tempUser.setAppId("AA");
		System.out.println(tempUser);
	}
	
	@Test
	public void tempUserDaoTest(){
		//Integer count=tempUserDao.getCount("oRbkdwh8_Nrg6SkLDmJA3bURohrw", "wx4d2cb86c1256cbe");
		//System.out.println(count);
		TempUser tempUser=tempUserDao.getTempUser("oRbkdwh8_Nrg6SkLDmJA3bURohrw", "wx4d2cb896c1256cbe");
		System.out.println(tempUser);
	}
	
}
