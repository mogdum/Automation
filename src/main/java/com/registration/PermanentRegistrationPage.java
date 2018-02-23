package com.registration;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PermanentRegistrationPage {

	WebDriver driver;

	// Locators of permanent registration
	
	By patcate = By.name("PATIENT_CAT");
	By titl = By.name("TITLE");
	By patfstname = By.name("PNT_NAME");
	By lastname = By.name("LAST_NAME");
	By dob = By.name("DOB");
	By age = By.name("AGE");
	By gender = By.name("SEX");
	By maritalstatus = By.name("MTRL_STATUS");
	By religion = By.name("RELIGION");
	By primarylanguage = By.name("PLANGUAGE");
	By address1 = By.name("ADDRESS1");
	By phone = By.name("MOBILE_NO");
	By country = By.name("COUNTRY_CODE");
	
	By relation = By.name("RELATION");
	By patientidentifier = By.name("PAT_IDENTITY");
	By panno = By.name("PAT_IDENTITY_PROOF");
	By nationality = By.name("NATIONALITY");
	By vip = By.name("IS_MLC");
	By education = By.name("EDUCATION");
	By occupation = By.name("OCCUPATION");
	By bloodgroup = By.name("BLOOD_GRP_CODE");
	By citizenship = By.name("CITIZENSHIP");
	By seniorcitizen = By.name("SC_PROOF");
	By pinorZip = By.name("ZIP");
	By save = By.name("submit");
	By browse = By.name("image");
	By reg = By.linkText("Search Registration");
	By search = By.name("search");
	By mrno=By.xpath("//form/table[2]/tbody/tr/td[2]");
	
	public PermanentRegistrationPage(WebDriver localDriver) {
		this.driver = localDriver;
	}

	public String savePermanentRegistration(Map<String, String> samples)
	{
		new Select(driver.findElement(patcate)).selectByVisibleText(samples.get("patientcategory"));
		new Select(driver.findElement(titl)).selectByVisibleText(samples.get("title"));
		driver.findElement(patfstname).sendKeys(samples.get("firstname"));
		driver.findElement(lastname).sendKeys(samples.get("lastname"));
		driver.findElement(dob).sendKeys(samples.get("dob"));
		driver.findElement(age).sendKeys(samples.get("age"));
		new Select(driver.findElement(gender)).selectByVisibleText(samples.get("gender"));
		new Select(driver.findElement(maritalstatus)).selectByVisibleText(samples.get("maritalstatus"));
		new Select(driver.findElement(religion)).selectByVisibleText(samples.get("religion"));
		new Select(driver.findElement(primarylanguage)).selectByVisibleText(samples.get("primarylanguage"));
		driver.findElement(address1).sendKeys(samples.get("address1"));
		driver.findElement(phone).sendKeys(samples.get("phone"));
		new Select(driver.findElement(country)).selectByVisibleText(samples.get("country"));
		
		new Select(driver.findElement(relation)).selectByVisibleText(samples.get("relation"));
		new Select(driver.findElement(patientidentifier)).selectByVisibleText(samples.get("patientidentifier"));
		driver.findElement(panno).sendKeys(samples.get("panno"));
		new Select(driver.findElement(nationality)).selectByVisibleText(samples.get("nationality"));
		new Select(driver.findElement(vip)).selectByVisibleText(samples.get("vip"));
		new Select(driver.findElement(education)).selectByVisibleText(samples.get("education"));
		new Select(driver.findElement(occupation)).selectByVisibleText(samples.get("occupation"));
		new Select(driver.findElement(bloodgroup)).selectByVisibleText(samples.get("bloodgroup"));
		new Select(driver.findElement(citizenship)).selectByVisibleText(samples.get("citizenship"));
		new Select(driver.findElement(seniorcitizen)).selectByVisibleText(samples.get("seniorcitizen"));
		driver.findElement(pinorZip).sendKeys(samples.get("pinorzip"));
		
		driver.findElement(browse).sendKeys("E:\\kella.txt");
		driver.findElement(save).click();
		
		String savemsg = driver.switchTo().alert().getText();
      
		driver.switchTo().alert().accept();
		
		return savemsg;
		
	}
	
	public boolean  searchRegistration(String mrNo){
		
		driver.findElement(reg).click();
		driver.findElement(search).sendKeys(mrNo);
		String mrNo1=driver.findElement(mrno).getText();
		return mrNo.equals(mrNo1);
	}
	
}
