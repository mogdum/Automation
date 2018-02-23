package com.login;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class LoginPage {
	static Logger log = Logger.getLogger(LoginPage.class.getName());
	
	WebDriver driver;
	
	
	By userName=By.name("username");
	By pwd=By.name("password");
	By submit=By.name("submit");
	
	public LoginPage(WebDriver localDriver) {
		driver=localDriver;
	}
	
	public WebElement identifyWebElementExplicitWait(By locator){
		WebDriverWait wait=new WebDriverWait(driver, 30);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}
	
	public WebElement identifyWebElementFluentWait(By locator){
	
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
		         .ignoring(ElementNotFoundException.class,ElementNotVisibleException.class);
	WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
		
		return element;
	}
	
	
	public void login(String user,String pwd1){
		try {
			
			driver.findElement(userName).sendKeys(user);
			WebElement element=identifyWebElementFluentWait(pwd);
			element.sendKeys(pwd1);
			//driver.findElement(pwd).sendKeys(pwd1);
			log.info("username and passwords are entered"+user+","+pwd1);
			
			driver.findElement(submit).click();
		} catch (Exception e) {
			log.error("login failed"+e);
		}
				
	}
	
	
	

	
	public void logout(){
		
		driver.findElement(By.linkText("Logout")).click();
		
	}

}
