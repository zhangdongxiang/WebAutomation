package com.oradt.test.tasks;

//import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;

import com.oradt.test.core.BrowserEmulator;

public class ScheduleTasks {
	private static Logger log = Logger.getLogger(LoginTasks.class);
	private BrowserEmulator be = null;

	public ScheduleTasks(BrowserEmulator browser) {
		be = browser;
	}

	public void clickscheduleMenu(Map<String, String> data) {
		log.info("进入' 日程'");
		be.click(data.get("menu_schedule"));
	}


	
//	public void searchPostman(Map<String, String> data) {
//		log.info("------查找 投递员:" + name + "------");
//		be.enterFrame(data.get("frame"));
//		be.type("//input[@name='postmanname']", name);
//		be.click(data.get("btn_search"));
//		be.pause(3000);
//
//	}

//	public void verifyPostmanNotInList() {
//		be.expectElementExistOrNot(true,
//				"//table[@id='tblMain']//td[@class='dataTables_empty']", 3000);
//		log.info("测试通过——没有找到投递员:" + name);
//	}

//	public void verifyPostmanInList() {
//		List<WebElement> results = be.getBrowserCore().findElements(
//				By.xpath("//table[@id='tblMain']/tbody/tr[1]/td"));
//
//		try {
//			if (results != null && results.size() > 1) {
//				Assert.assertEquals(results.get(2).getText(), name, "名字不匹配");
//				Assert.assertEquals(results.get(3).getText(), account, "机构不匹配");
//				Assert.assertEquals(results.get(4).getText(), phone, "机构名称不匹配");
//				log.info("测试通过——找到投递员:" + name);
//				return;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		be.handleFailure("查询失败，没有找到投递员:" + name);
//	}


}
