package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.login.LoginPage;

public class BaseTestClass {
	protected WebDriver driver;
	
	protected String excelPath;
	protected String sheetName;
	protected String projectPath;
	Properties prop;
	
	
	Map<String, List<Map<String, String>>> testCasesWithSamples = new LinkedHashMap<String, List<Map<String, String>>>();

	public BaseTestClass() throws Exception{
		System.out.println("super class");
		projectPath=System.getProperty("user.dir");
	System.out.println("project path"+projectPath);	
	projectPath=projectPath.replace("\\", "\\\\");
	
	prop=new Properties();
	
	FileInputStream fis=new FileInputStream(projectPath+"\\src\\test\\resources\\properties\\applicationUnderTest.properties");
	prop.load(fis);
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("setting the drivers and opening the browser");
		System.setProperty("webdriver.gecko.driver", projectPath+"\\src\\test\\resources\\drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(prop.getProperty("url"));
	}



	@BeforeClass
	public void beforeClass() throws Exception {
		testSamplesReading(excelPath, sheetName);
	}

	@DataProvider(name = "testdata")
	public Object[][] testData(Method method) throws Exception {
		List<Map<String, String>> samples = testCasesWithSamples.get(method.getName());
		Object[][] obj = null;
		if (samples == null) {
			obj = new Object[0][0];
		} else {
			obj = new Object[samples.size()][2];
		}
		int i = 0;
		for (Map<String, String> sample : samples) {
			obj[i][0] = sample;
			obj[i][1] = sample.get("Result");
			i++;
		}
		return obj;
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result)throws Exception{
		if(ITestResult.FAILURE==result.getStatus()){
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			
			String methodName=result.getMethod().getMethodName();
			Integer count=result.getMethod().getCurrentInvocationCount();
			
			FileUtils.copyFile(source, new File(projectPath+
			"\\src\\test\\resources\\screenshots\\"+methodName+count+".png"));
			
		}
		
		
	}
	
	@AfterSuite
	public void closeBrowser(){
		driver.quit();
	}

	public void testSamplesReading(String filePath, String sheetName) throws Exception {
		String resource = filePath;
		FileInputStream fis = new FileInputStream(resource);
		Workbook book = null;
		Sheet sheet = null;
		if (resource.endsWith("xlsx")) {
			book = new XSSFWorkbook(fis);
		} else if (resource.endsWith("xls")) {
			book = new HSSFWorkbook(fis);
		}
		sheet = book.getSheet(sheetName);
		List<String> headersList = null;
		List<String> dataList = null;
		Map<String, String> sample = null;
		List<Map<String, String>> samples = null;

		for (Row row : sheet) {

			if (row.getCell(0).toString().startsWith("Header")) {
				headersList = new ArrayList<String>();
				samples = new ArrayList<Map<String, String>>();
				for (Cell cell : row) {
					headersList.add(cell.toString());
				}
			} else {
				dataList = new ArrayList<String>();
				for (Cell cell : row) {
					dataList.add(cell.toString());
				}
				sample = new LinkedHashMap<String, String>();

				for (int i = 0; i < dataList.size(); i++) {
					sample.put(headersList.get(i), dataList.get(i));
				}
				samples.add(sample);

				if (testCasesWithSamples.get(dataList.get(0)) == null) {
					testCasesWithSamples.put(dataList.get(0), samples);
				}

			}

		}
		System.out.println(testCasesWithSamples);
	}

}
