package com.kdf.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;

import com.kdf.keyword.Keywords;

public class ChromeDriverScript {
	
	public static void main(String[] args) throws IOException {
		WebDriver driver = null;		
		
		File file = new File("C:\\Users\\rochelle\\Documents\\Automated Testing References\\Test Case 1.xls");
		FileInputStream fis = new FileInputStream(file);
		
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheetAt(0);
		
		ArrayList<Cell> steps = new ArrayList<Cell>();
		Keywords keys = new Keywords(driver, "chrome");
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				steps.add(cell);		
				System.out.println(cell);
			}
			
			if (steps.size() == 1) {
				keys.callMethod(steps.get(0).toString());
			} else if (steps.size() == 2) {
				keys.callMethod(steps.get(0).toString(), steps.get(1).toString());
			} else {
				keys.callMethod(steps.get(0).toString(), steps.get(1).toString(), steps.get(2).toString());
			}
			
			steps.clear();
		}
		wb.close();
	}
}
