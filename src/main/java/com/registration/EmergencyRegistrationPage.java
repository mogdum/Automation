package com.registration;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class EmergencyRegistrationPage {

	WebDriver driver;
	By patCat = By.name("PATIENT_CAT");
	By title = By.name("TITLE");
	By patName = By.name("PNT_NAME");
	By patLastName = By.name("LAST_NAME");
	By dob = By.name("DOB");
	By age = By.name("AGE");
	By gender = By.name("SEX");
	By patIdentity = By.name("PAT_IDENTITY");
	By panNO = By.name("PAT_IDENTITY_PROOF");
	By bloodGroup = By.name("BLOOD_GRP_CODE");
	By addr1 = By.name("ADDRESS1");
	By phone1 = By.name("MOBILE_NO");
	By zip = By.name("ZIP");
	By submit = By.name("submit");
	By submitImg = By.name("image");

	By regLink = By.linkText("Registration");
	By emrLink = By.linkText("Emergency Registration");

	public EmergencyRegistrationPage(WebDriver localWebDriver) {
		driver = localWebDriver;
	}

	public String emergencyRegistration(Map<String, String> samples) {
		driver.findElement(regLink).click();
		driver.findElement(emrLink).click();

		new Select(driver.findElement(patCat)).selectByVisibleText(samples.get("patCat"));

		new Select(driver.findElement(title)).selectByVisibleText(samples.get("title"));

		driver.findElement(patName).sendKeys(samples.get("firstname"));
		driver.findElement(patLastName).sendKeys(samples.get("lastname"));
		driver.findElement(dob).sendKeys(samples.get("dob"));
		driver.findElement(age).sendKeys(samples.get("age"));

		new Select(driver.findElement(gender)).selectByVisibleText(samples.get("gender"));

		driver.findElement(addr1).sendKeys(samples.get("address1"));
		driver.findElement(phone1).sendKeys(samples.get("phone"));

		new Select(driver.findElement(patIdentity)).selectByVisibleText(samples.get("patIdentifier"));

		driver.findElement(panNO).sendKeys(samples.get("pan"));

		new Select(driver.findElement(bloodGroup)).selectByVisibleText(samples.get("blood group"));

		driver.findElement(zip).sendKeys(samples.get("pin or zip"));
		driver.findElement(submitImg).sendKeys("E:\\kella.txt");
		driver.findElement(submit).click();
		
		String text = driver.switchTo().alert().getText();
		 driver.switchTo().alert().accept();
		return text;
	}

}
