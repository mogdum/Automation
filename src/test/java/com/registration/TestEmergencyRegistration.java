package com.registration;

import java.util.Map;

import org.testng.annotations.Test;

import com.util.BaseTestClass;
import com.util.BaseTestClassWithLogin;

public class TestEmergencyRegistration extends BaseTestClassWithLogin{
	
	public TestEmergencyRegistration()throws Exception {
		System.out.println("chiild class");
		excelPath=projectPath+"\\src\\test\\resources\\testdata\\registration.xlsx";
		sheetName="emergency";
	}

	
	
	@Test(dataProvider="testdata")
	public void emergencyRegistration(Map<String,String> samples,String result){
		EmergencyRegistrationPage page=new EmergencyRegistrationPage(driver);
		String text=page.emergencyRegistration(samples);
	    System.out.println(text);
		
	}

}
