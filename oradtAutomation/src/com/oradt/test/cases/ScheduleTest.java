
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
public class ScheduleTest extends BaseTestCase {
	private static BrowserEmulator be ;	
	private static LoginTasks loginTask;
	private static ScheduleTasks scheduleTask;
//	private static String schedule_name = "schedule_" + TaskUtils.generateRandomString(5);
//	private static String schedule_newname = "schedule_" + TaskUtils.generateRandomString(5);
//	private static String schedule_phone=TaskUtils.generateRandomPhoneNumber();
	
	@BeforeClass
	public void doBeforeClass() {
		be = new BrowserEmulator();	
		loginTask= new LoginTasks(be);
		scheduleTask=new ScheduleTasks(be);
	}
	
	@Test(dataProvider = "menu")
	public void enterscheduleMenu(Map<String, String> data) {		
		// login admin
		loginTask.loginHome(be, data);		
		scheduleTask.clickscheduleMenu(data);
		//退出系统
		loginTask.logout(be, data);	
	}
	
//	
//	@Test(dependsOnMethods = "enterscheduleMenu", dataProvider = "schedule")
//	public void createschedule(Map<String, String> data) {
//		//创建
//		scheduleTask.setName(schedule_name);
//		scheduleTask.setPhone(schedule_phone);
//		scheduleTask.setAccount(GlobalSettings.getProperty("AccountName"));
//		scheduleTask.createschedule(data);
//	}
//	
//	@Test(dependsOnMethods = "createschedule", dataProvider = "schedule")
//	public void searchschedule(Map<String, String> data) {
//		//搜索
//		scheduleTask.searchschedule(data);
//		scheduleTask.verifyscheduleInList();
//	}
//
//	@Test(dependsOnMethods = "searchschedule", dataProvider = "schedule")
//	public void editschedule(Map<String, String> data) {
//		//搜索
//		scheduleTask.setName(schedule_newname);
//		scheduleTask.editschedule(data);
//		scheduleTask.searchschedule(data);
//		scheduleTask.verifyscheduleInList();
//	}
//
//	@Test(dependsOnMethods = "editschedule", dataProvider = "schedule")
//	public void deleteschedule(Map<String, String> data) {
//		//选择,点击删除		
//		//scheduleTask.deleteschedule(data);
//		//搜索已删除
//		//scheduleTask.searchschedule(data);
//		
//		//scheduleTask.verifyscheduleNotInList();
//		//退出系统
//		loginTask.logout(be);	
//	}
//	
//	

	@DataProvider(name = "menu")
	public Iterator<Object[]> dataforschedule(Method method)
			throws IOException {
		return new TestDataHandler("Schedule", "SC");
	}
	
	@AfterClass(alwaysRun = true)
	public void doAfterClass() {		
		be.quit();
	}
}
