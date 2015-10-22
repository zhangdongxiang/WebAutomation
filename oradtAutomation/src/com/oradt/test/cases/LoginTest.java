
package com.oradt.test.cases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oradt.test.core.BrowserEmulator;
//import com.oradt.test.core.GlobalSettings;
import com.oradt.test.core.TestDataHandler;
import com.oradt.test.tasks.LoginTasks;
import com.oradt.test.tasks.ScheduleTasks;
//import com.oradt.test.tasks.TaskUtils;

/**
 * @Description:个人账户->日程  
 * 
 * @author： zhangdongxiang@oradt.com
 *
 * @date：      09/07/2015
 * 
 */
public class LoginTest extends BaseTestCase {
	private static BrowserEmulator be ;	
	private static LoginTasks loginTask;

	@BeforeClass
	public void doBeforeClass() {
		be = new BrowserEmulator();	
		loginTask= new LoginTasks(be);
	}
	
	@Test(dataProvider = "menu")
	public void IsUserIconPresent(Map<String, String> data) {		
		// login personal account
		loginTask.openPersonalUrl(be, data);
		loginTask.IsUserIconPresentOrNot(data);
	}
	
	@Test(dataProvider = "menu")
	public void IsPasswdIconPresent(Map<String, String> data) {		
		// login personal account
		loginTask.openPersonalUrl(be, data);
		loginTask.IsPasswdIconPresentOrNot(data);
	}

	@Test(dataProvider = "menu")
	public void IsLoginButtonPresent(Map<String, String> data) {		
		// login personal account
		loginTask.openPersonalUrl(be, data);
		loginTask.IsLoginButtonPresentOrNot(data);
	}
	
	@Test(dataProvider = "menu")
	public void IsRegistePresent(Map<String, String> data) {		
		// login personal account
		loginTask.openPersonalUrl(be, data);
		loginTask.IsRegistePresentOrNot(data);
	}
	
	@Test(dataProvider = "menu")
	public void IsForgetPasswdPresent(Map<String, String> data) {		
		// login personal account
		loginTask.openPersonalUrl(be, data);
		loginTask.IsForgetPasswdPresentOrNot(data);
	}
	
	@Test(dataProvider = "menu")
	public void IsRemberPasswdPresent(Map<String, String> data) {		
		// login personal account
		loginTask.openPersonalUrl(be, data);
		loginTask.IsRemberPasswdPresentOrNot(data);
	}
	
	@Test(dataProvider = "menu")
	public void IsRemberPasswdCheckBoxPresent(Map<String, String> data) {		
		// login personal account
		loginTask.openPersonalUrl(be, data);
		loginTask.IsRemberPasswdCheckBoxPresentOrNot(data);
	}
	
	
	@Test(dataProvider = "menu")
	public void personalAccountLogin(Map<String, String> data) {		
		// login personal account
		loginTask.loginHome(be, data);
	}

	@Test(dataProvider = "menu")
	public void personalAccountLogout(Map<String, String> data) {		
		// login personal account
		loginTask.logout(be, data);	
	}
	
	@DataProvider(name = "menu")
	public Iterator<Object[]> dataforLogin(Method method)
			throws IOException {
		return new TestDataHandler("Login", "lg");
	}
	
	@AfterClass(alwaysRun = true)
	public void doAfterClass() {		
		be.quit();
	}
}
