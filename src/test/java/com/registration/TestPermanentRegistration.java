package com.registration;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.util.BaseTestClassWithLogin;

public class TestPermanentRegistration extends BaseTestClassWithLogin {

	public TestPermanentRegistration() throws Exception {
		excelPath = projectPath + "\\src\\test\\resources\\testdata\\registration.xlsx";
		sheetName = "emergency";

	}

	@Test(dataProvider = "testData")
	public void permanentRegistration(Map<String, String> samples, String Result) {

		PermanentRegistrationPage page = new PermanentRegistrationPage(driver);
		String msg = page.savePermanentRegistration(samples);

		Assert.assertTrue(msg.contains(samples.get("conform")), "not registred");

		String[] array = msg.split(" ");
		String marno = array[4];

		Assert.assertTrue(page.searchRegistration(marno), "not matched");

	}

	/*
	 * @Test(dataProvider = "testData") public void
	 * searchRegistration(Map<String, String> samples, String Result) {
	 * 
	 * PermanentRegistrationPage page = new PermanentRegistrationPage(driver);
	 * String msg = page.savePermanentRegistration(samples); String[]
	 * array=msg.split(" "); String marno=array[4];
	 * Assert.assertTrue(msg.contains(samples.get("conform")), "not registred");
	 * 
	 * }
	 */

}
