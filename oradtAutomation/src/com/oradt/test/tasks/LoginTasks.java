package com.oradt.test.tasks;

import java.util.Map;

import org.apache.log4j.Logger;

import com.oradt.test.core.BrowserEmulator;
import com.oradt.test.core.GlobalSettings;
//import com.oradt.test.core.LogTools;

public class LoginTasks {
	private static Logger log = Logger.getLogger(LoginTasks.class.getName());
	private static String url = GlobalSettings.getProperty("LoginURL");
	private static String name = GlobalSettings.getProperty("LoginName");
	private static String passwd = GlobalSettings.getProperty("LoginPWD");
	//private static String account = GlobalSettings.getProperty("AccountName");
	private BrowserEmulator be = null;
	
	public LoginTasks(BrowserEmulator browser) {
		be = browser;
	}

	public void openPersonalUrl(BrowserEmulator browser, Map<String, String> data)
	{
		log.info ("open url: '" + url + "'");
		browser.open(url);
		browser.click(data.get("persoanl_Account"));
	}
	
	public void loginHome(BrowserEmulator browser, Map<String, String> data) {
		log.info ("登陆oradt: '" + name + "' password: '"
				+ passwd + "'");

		browser.open(url);
		browser.click(data.get("persoanl_Account"));
		browser.type(data.get("personal_name"), name);
		browser.type(data.get("persoanl_passwd"), passwd);
		browser.click(data.get("personal_Login_Button"));		
		browser.getBrowserCore().manage().window().maximize();
		browser.pause(500);
		browser.expectElementExistOrNot(true, "//i[@title='zhangdongxiang']", 5000);
		log.info("登陆成功！");
	}

	public void logout(BrowserEmulator browser, Map<String, String> data) {		
		log.info("退出系统: '" + name + "'");
	
		browser.click(data.get("persoanl_Logout_Button"));
		browser.expectElementExistOrNot(true, data.get("persoanl_Account"), 5000);
	}
	
	public void IsRemberPasswdPresentOrNot(Map<String, String> data)
	{
		be.expectTextExistOrNot(true, data.get("rember_Passwd_text"), 2000);
	}
	
	public void IsRegistePresentOrNot(Map<String, String> data)
	{
		be.expectTextExistOrNot(true, data.get("register_text"), 2000);
	}
	
	public void IsForgetPasswdPresentOrNot(Map<String, String> data)
	{
		be.expectTextExistOrNot(true, data.get("forget_Passwd_text"), 2000);
	}
	
	public void IsRemberPasswdCheckBoxPresentOrNot(Map<String, String> data)
	{
		be.expectElementExistOrNot(false, data.get("rember_Passwd_Icon"), 2000);
	}
	
	public void IsUserIconPresentOrNot(Map<String, String> data)
	{
		be.expectElementExistOrNot(true, data.get("user_Icon"), 2000);
	}
	
	public void IsPasswdIconPresentOrNot(Map<String, String> data)
	{
		be.expectElementExistOrNot(true, data.get("passwd_Icon"), 2000);
	}
	
	public void IsLoginButtonPresentOrNot(Map<String, String> data)
	{
		be.expectElementExistOrNot(true, data.get("personal_Login_Button"), 2000);
	}
}
