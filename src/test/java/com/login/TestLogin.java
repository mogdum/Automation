package com.login;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.util.BaseTestClass;

public class TestLogin extends BaseTestClass{
	
	static Logger log = Logger.getLogger(TestLogin.class.getName());
	public TestLogin()throws Exception {
		System.out.println("chiild class");
		excelPath=projectPath+"\\src\\test\\resources\\testdata\\login.xlsx";
		sheetName="login";
	}

	@Test(dataProvider = "testdata")
	public void loginPositive(Map<String,String> sample, String Result) {
    LoginPage page=new LoginPage(driver);		
	page.login(sample.get("uesrname"), sample.get("password"));

		log.info("method excecution completed");
	}
	

	
	
	
	
	@Test(dataProvider = "testdata")
	public void loginNegative(Map<String,String> sample, String Result) {
        System.out.println("test method");
		LoginPage page = new LoginPage(driver);
		page.login(sample.get("uesrname"), sample.get("password"));
		String message=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertTrue(message.contains("Wrong1!"),"wrong alert message");
		//Assert.assertEquals(message.trim(),sample.get("alertMag").trim());
		//System.out.println(message);
		

	}
	
	

	


}
