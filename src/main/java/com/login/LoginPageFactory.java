package com.login;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {
	static Logger log = Logger.getLogger(LoginPageFactory.class.getName());
	
	WebDriver driver;
	
	@FindBy(name="username") WebElement userName;
	@FindBy(name="password") WebElement pwd;
	@FindBy(how=How.NAME, using="submit1")
	WebElement submit;
	
	/*By userName=By.name("username");
	By pwd=By.name("password");
	By submit=By.name("submit");*/
	
	
	public LoginPageFactory(WebDriver localDriver) {
		driver=localDriver;
		PageFactory.initElements(driver, this);
	}
	
	public void login(String user,String pwd1){
		try {
			userName.sendKeys(user);
			pwd.sendKeys(pwd1);
			log.info("username and passwords are entered"+user+","+pwd1);
			submit.click();
		} catch (Exception e) {
			log.error("login failed"+e);
		}
	}
	
	public void logout(){
		driver.findElement(By.linkText("Logout")).click();
		
	}

}
