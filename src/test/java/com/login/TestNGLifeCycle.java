package com.login;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNGLifeCycle {

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("setting the drivers and opening the browser");

	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("login functionality");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("test data readding");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("common functionality for test method ");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("capturing results and screen shots");
	}

	@Test()
	public void method1() {
		System.out.println("test method1");
	}

	/*
	 * @DataProvider public Object[][] dp() { return new Object[][] { new
	 * Object[] { 1, "a" }, new Object[] { 2, "b" }, }; }
	 */

	@AfterClass
	public void afterClass() {
		System.out.println("cleanup the data");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("logout from the application");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("close the browser");
	}

}
