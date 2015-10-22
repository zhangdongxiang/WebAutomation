package com.oradt.test.tasks;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.oradt.test.core.BrowserEmulator;

public class TrunkTasks {
	private static final Logger log = Logger.getLogger(LoginTasks.class);
	private String account = "";
	private String status = "";
	private String phone = "";
	private BrowserEmulator be = null;
	private String texttrunk = "";
	private String zt = "";
	
	public TrunkTasks(BrowserEmulator browser) {
		be = browser;
	}
	
	
	
	public void clickTrunkMenu(Map<String, String> data) {
		log.info("进入' 干线任务管理'");
		be.click(data.get("menu_zpt"));
		be.click(data.get("menuitem_trunktask"));
	}
	
	//导入功能
	public void lead(String[][] dataTrunk,Map<String, String> data) {		

		
		log.info("------11111111111:" + dataTrunk + "------");
		log.info("------22222222222:" + data + "------");
		TaskUtils.generateImportFile("TrunkData",dataTrunk);
		String fileaddress = System.getProperty("user.dir")+"\\resource\\data\\TrunkData_temp.xls";
		log.info("------文件地址:" + fileaddress + "------");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		//点击导入按钮
		be.click("//a[text()='导入']");
		
		be.enterFrame(data.get("frame"));
		//发送文件地址
		be.getBrowserCore().findElement(By.cssSelector("#edtFile")).sendKeys(fileaddress);
		//点击覆盖
		be.click("//label[@class='radio'][2]/i");
		//be.leaveFrame();

		//be.enterFrame(data.get("frame"));
		//点击下一步
		be.click("//form[@id='frmInfo']/footer/button[1]");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		//点击下一步
		be.click("//form[@id='import_form']/footer/button[2]");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		//点击返回干线任务管理
		be.click("//button[@class='btn btn-primary pull-left']");
		be.leaveFrame();
		
	}
	
	
	//导入货品功能
	public void addGoods(String[][] addgoods,Map<String, String> data) {		

		TaskUtils.generateImportFile("addgoods",addgoods);
		String fileaddress = System.getProperty("user.dir")+"\\resource\\data\\addgoods_temp.xls";
		log.info("------文件地址:" + fileaddress + "------");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		//点击导入货品按钮
		be.click("//a[text()='导入货品']");
		be.enterFrame(data.get("frame"));
		//发送文件地址
		be.getBrowserCore().findElement(By.cssSelector("#edtFile")).sendKeys(fileaddress);
		//点击覆盖
		be.click("//label[@class='radio'][2]/i");
		//be.leaveFrame();

		//be.enterFrame(data.get("frame"));
		//点击下一步
		be.click("//form[@id='frmInfo']/footer/button[1]");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		//点击下一步
		be.click("//form[@id='import_form']/footer/button[2]");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		//点击返回干线任务管理
		be.click("//button[@class='btn btn-primary pull-left']");
		be.leaveFrame();
	}	
	
	//查询任务A
	public void searchTrunkA(String[][] dataTrunk,Map<String, String> data) {
		
		log.info("------查找 任务A:" + dataTrunk[0][0] + "------");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		be.type("//input[@name='taskcode']", dataTrunk[0][0]);
		be.pause(3000);
		be.click(data.get("btn_search"));
		be.pause(3000);
		//查询结果中的任务号
		texttrunk = be.getBrowserCore().findElement(By.xpath("//tbody[@role='alert']/tr/td[3]")).getText();
		log.info("**********************************实际任务号" + texttrunk + "------");
		Assert.assertEquals(texttrunk, dataTrunk[0][0]);
		log.info("**********************************查询任务A 测试通过");
		//查询结果中的任务状态
		status = be.getText(data.get("issign_no"));
		log.info("**********************************实际状态" + status + "------");
		Assert.assertEquals(status, "未确认");
		log.info("**********************************查询任务A，状态正确，测试通过");
		//查询 状态快捷菜单中的该状态名下的状态个数
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		be.click("//span[@class='chat-list-open-close']");
		zt = be.getBrowserCore().findElement(By.xpath("//div[@class='open']/div/ul/li[3]")).getText();
		log.info("**********************************实际状态" + zt + "------");
		Assert.assertEquals(be.getBrowserCore().findElement(By.xpath("//div[@class='open']/div/ul/li[3]")).getText(), "未确认(1)");
		log.info("**********************************查询任务A，快捷状态菜单 ，状态正确，测试通过");
		
		//be.leaveFrame();
		//be.enterFrame(data.get("frame"));
		be.click("//span[@class='chat-list-open-close']");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		
		//选中任务，点击变更状态（在途中状态）判断状态变为在途中状态。状态快捷菜单中在途中为1
		be.click("//tbody[@role='alert']/tr/td[1]");
		//点击变更状态按钮
		be.click(data.get("change_state"));
		//变更为在途中
		be.click("//fieldset[@class='no-padding']//section/div/div/select/option[3]");
		//点击确定
		be.click("//div[@id='myModal']/div/div/div[3]/button[1]");
		//变更为在途中后的判断
		
		be.click("//span[@class='chat-list-open-close']");		
		zt = be.getText("//div[@id='taskPanel']/ul/li[5]/a");
		log.info("**********************************实际状态2" + zt + "------");
		Assert.assertEquals(zt, "在途中(1)");
		log.info("**********************************查询任务A，变更后状态正确，快捷状态菜单 状态正确，测试通过");
		be.click("//span[@class='chat-list-open-close']");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		//选中任务，点击状态回退（未开始状态）判断状态变为未开始状态。状态快捷菜单中未开始为1
		be.click("//tbody[@role='alert']/tr/td[1]");
		//点击状态回退按钮
		be.click("//div[@id='wid-id-0']/div/div/div[3]/div/div[5]/a[2]");
		//变更为未开始
		be.click("//select[@id='statuslist_b']/option[3]");
		//点击确定
		be.click("//div[@id='myModal_s']/div/div/div[3]/button[1]");
		//变更为在途中后的判断	
		be.click("//span[@class='chat-list-open-close']");		
		zt = be.getText("//div[@id='taskPanel']/ul/li[4]/a");
		log.info("**********************************实际状态" + zt + "------");
		Assert.assertEquals(zt, "未开始(1)");
		log.info("**********************************查询任务A，状态回退后状态正确，快捷状态菜单 状态正确，测试通过");
		be.click("//span[@class='chat-list-open-close']");	
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		
		//选中任务，点击变更状态（完成状态）判断状态变为完成状态。状态快捷菜单中完成为1，完成为1
		be.click("//tbody[@role='alert']/tr/td[1]");
		//点击变更状态按钮
		be.click(data.get("change_state"));
		//变更为完成状态
		be.click("//select[@id='statuslist']/option[text()='完成']");
		//点击确定
		be.click("//div[@id='myModal']/div/div/div[3]/button[1]");
		//变更为完成后的判断
		be.click("//span[@class='chat-list-open-close']");	
		zt = be.getText("//div[@id='taskPanel']/ul/li[7]/dl/dd[1]/a");
		log.info("**********************************实际状态" + zt + "------");
		Assert.assertEquals(zt, "完成(1)");
		log.info("**********************************查询任务A，变更后状态正确，快捷状态菜单 状态正确，测试通过");
		be.click("//span[@class='chat-list-open-close']");	
		be.leaveFrame();
	}
	//查询任务B
	public void searchTrunkB(String[][] dataTrunk,Map<String, String> data) {
		//dataTrunk[1][0]="2014122409";
		log.info("------查找 任务B:" + dataTrunk[1][0] + "------");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		be.type("//input[@name='taskcode']", dataTrunk[1][0]);
		be.pause(3000);
		be.click(data.get("btn_search"));
		be.pause(3000);
		//查询结果中的任务号
		texttrunk = be.getText("//tbody[@role='alert']/tr/td[3]");
		log.info("**********************************实际任务号" + texttrunk + "------");
		Assert.assertEquals(texttrunk, dataTrunk[1][0]);
		log.info("**********************************查询任务B 测试通过");
		//查询结果中的任务状态
		status = be.getText("//tbody[@role='alert']/tr/td[4]");
		log.info("**********************************实际状态" + status + "------");
		Assert.assertEquals(status, "未下发");
		log.info("**********************************查询任务B，状态正确，测试通过");
		//查询 状态快捷菜单中的该状态名下的状态个数
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		be.click("//span[@class='chat-list-open-close']");
		zt = be.getBrowserCore().findElement(By.xpath("//div[@class='open']/div/ul/li[2]")).getText();
		log.info("**********************************实际状态" + zt + "------");
		Assert.assertEquals(zt, "未下发(1)");
		log.info("**********************************查询任务B，快捷状态菜单 ，状态正确，测试通过");
		
		//be.leaveFrame();
		//be.enterFrame(data.get("frame"));
		be.click("//span[@class='chat-list-open-close']");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		
		//选中任务，点击改期。状态变为完成。完成类型为改期。状态快捷菜单中完成为1，改期为1
		be.click("//tbody[@role='alert']/tr/td[1]");
		//点击改期按钮
		be.click("//div[@id='wid-id-0']/div/div/div[3]/div/div[4]/a[2]");
		//改期备注
		be.type("//textarea[@id='selArea']", "aaaaaaaaaaaaaaaaaaaaaa");
		//点击确定
		be.click("//button[@id='submitBtn']");
		//改期按钮后的判断
		
		be.click("//div[@id='resultModal']/div/div/div/button");	
		be.click("//span[@class='chat-list-open-close']");
		
		zt = be.getText("//div[@id='taskPanel']/ul/li[7]/dl/dd[2]/a");
		log.info("**********************************实际状态2" + zt + "------");
		Assert.assertEquals(zt, "改期(1)");
		log.info("**********************************查询任务B，变更后状态正确，快捷状态菜单 状态正确，测试通过");
		be.click("//span[@class='chat-list-open-close']");
		be.leaveFrame();
		//be.enterFrame(data.get("frame"));
	}
	
	//查询任务C
	public void searchTrunkC(String[][] dataTrunk,Map<String, String> data) {
		//查询C任务号。先看是不是未指派和状态快捷菜单中未指派为1，
		log.info("------查找 任务C:" + dataTrunk[2][0] + "------");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		be.type("//input[@name='taskcode']", dataTrunk[2][0]);
		be.pause(3000);
		be.click(data.get("btn_search"));
		be.pause(3000);
		//查询结果中的任务号
		texttrunk = be.getBrowserCore().findElement(By.xpath("//tbody[@role='alert']/tr/td[3]")).getText();
		log.info("**********************************实际任务号" + texttrunk + "------");
		Assert.assertEquals(texttrunk, dataTrunk[2][0]);
		log.info("**********************************查询任务C 测试通过");
		//查询结果中的任务状态
		zt = be.getText("//tbody[@role='alert']/tr/td[4]");
		log.info("**********************************实际状态" + zt + "------");
		Assert.assertEquals(zt, "未指派");
		log.info("**********************************查询任务C，状态正确，测试通过");
		//查询 状态快捷菜单中的该状态名下的状态个数
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		be.click("//span[@class='chat-list-open-close']");
		status = be.getBrowserCore().findElement(By.xpath("//div[@class='open']/div/ul/li[1]")).getText();
		log.info("**********************************实际状态" + status + "------");
		Assert.assertEquals(status, "未指派(1)");
		log.info("**********************************查询任务A，快捷状态菜单 ，状态正确，测试通过");
		
		//be.leaveFrame();
		//be.enterFrame(data.get("frame"));
		be.click("//span[@class='chat-list-open-close']");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		
		//选中任务给指定的投递员指派任务，判断状态变为未下发状态和状态快捷菜单中未指派为0，未下发为1
		be.click("//tbody[@role='alert']/tr/td[1]");
		//点击指派按钮
		be.click("//div[@id='wid-id-0']/div/div/div[3]/div/div[3]/a[1]");
		//写入投递员
		be.click("//div[@id='s2id_postmanname']/a/span[1]");
		be.type("//div[@id='select2-drop']/div/input", "aaa");
		be.click("//div[@id='select2-drop']/ul/li");
		//点击确定
		be.click("//button[@id='saveAppoint']");
		//关闭弹出框
		be.click("//div[@id='resultModal']/div/div/div/button");	
		//变更为未下发后的判断
		
		be.click("//span[@class='chat-list-open-close']");		
		zt = be.getText("//div[@id='taskPanel']/ul/li[2]/a");
		log.info("**********************************实际状态2" + zt + "------");
		Assert.assertEquals(zt, "未下发(1)");
		log.info("**********************************查询任务C，变更后状态正确，快捷状态菜单 状态正确，测试通过");
		be.click("//span[@class='chat-list-open-close']");
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		//选中任务点击下发，判断状态变为未确认状态和状态快捷菜单中未下发为0，未确认为1。
		be.click("//tbody[@role='alert']/tr/td[1]");
		//点击下发按钮
		be.click("//div[@id='wid-id-0']/div/div/div[3]/div/div[3]/a[2]");
		//关闭弹出框
		be.click("//div[@id='resultModal']/div/div/div/button");	
		
		//变更为未确认后的判断	
		be.click("//span[@class='chat-list-open-close']");		
		zt = be.getText("//div[@id='taskPanel']/ul/li[3]/a");
		log.info("**********************************实际状态" + zt + "------");
		Assert.assertEquals(zt, "未确认(1)");
		log.info("**********************************查询任务C，状态未确认状态正确，快捷状态菜单 状态正确，测试通过");
		be.click("//span[@class='chat-list-open-close']");	
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		
		//选选中任务，点击任务重置（只有未确认的状态才可以任务重置）判断状态变为未指派。
		be.click("//tbody[@role='alert']/tr/td[1]");
		//点击任务重置按钮
		be.click("//div[@id='wid-id-0']/div/div/div[3]/div/div[5]/a[3]");
		
		//点击确定
		be.click("//button[@id='bot1-Msg1']");
		be.pause(9000);
		//变更为未指派的判断
		be.click("//span[@class='chat-list-open-close']");	
		zt = be.getText("//div[@id='taskPanel']/ul/li[1]/a");
		log.info("**********************************实际状态" + zt + "------");
		Assert.assertEquals(zt, "未指派(1)");
		log.info("**********************************查询任务C，变更后为未指派状态正确，快捷状态菜单 状态正确，测试通过");
		be.click("//span[@class='chat-list-open-close']");	
		be.leaveFrame();
		be.enterFrame(data.get("frame"));
		
		
		//选中任务，点击取消。状态变为完成。完成类型为取消，状态快捷菜单中完成为1，取消为1
		be.click("//tbody[@role='alert']/tr/td[1]");
		//点击取消按钮
		be.click("//div[@id='wid-id-0']/div/div/div[3]/div/div[4]/a[1]");
		//取消备注
		be.type("//textarea[@id='selArea']", "aaaaaaaaaaaaaaaaaaaaaa");
		//点击确定
		be.click("//button[@id='submitBtn']");
		
		//关闭对话框
		be.click("//div[@id='resultModal']/div/div/div/button");	
		//取消按钮后的判断
		be.click("//span[@class='chat-list-open-close']");
		zt = be.getText("//div[@id='taskPanel']/ul/li[7]/dl/dd[3]/a");
		log.info("**********************************实际状态2" + zt + "------");
		Assert.assertEquals(zt, "取消(1)");
		log.info("**********************************查任务C，取消后状态正确，快捷状态菜单 状态正确，测试通过");
		be.click("//span[@class='chat-list-open-close']");
		be.leaveFrame();
		//be.enterFrame(data.get("frame"));
	}
}
