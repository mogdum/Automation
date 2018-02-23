package com.login;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataReading {

	static Map<String, List<Map<String, String>>> testCasesWithSamples =

			new LinkedHashMap<String, List<Map<String, String>>>();

	public static void main(String[] args) throws Exception {

		String resource = "E:\\testdata\\login.xlsx";

		FileInputStream fis = new FileInputStream(resource);
		Workbook book = null;
		Sheet sheet = null;

		if (resource.endsWith("xlsx")) {
			book = new XSSFWorkbook(fis);
		} else if (resource.endsWith("xls")) {
			book = new HSSFWorkbook(fis);
		}
		sheet = book.getSheet("login");

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
