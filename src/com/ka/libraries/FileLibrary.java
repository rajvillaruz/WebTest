package com.ka.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



import org.apache.commons.io.FilenameUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class FileLibrary {

	private String filePath;
	private File file;
	private FileInputStream fis;
	Workbook workBook;
	Scanner scanner;
	private static final char DEFAULT_SEPARATOR = ',';
	String testcase;
	
	
	public FileLibrary(String filePath, File file) throws FileNotFoundException  {
		this.filePath = filePath;
		this.file = file;
		this.fis = new FileInputStream(file);
	}
	
	public List<List<List<String>>> create() throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException {
		int numSheets = 1;
		List<List<String>> steps = null;
		List<List<List<String>>> tc = new ArrayList<List<List<String>>>();
		if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("xls")) { 
			workBook = WorkbookFactory.create(fis);
			numSheets = workBook.getNumberOfSheets();
			for (int i=0; i < numSheets; i++) {
				HSSFSheet sheet = (HSSFSheet) workBook.getSheetAt(i);
				steps = readFile(sheet);		
				if (!steps.isEmpty()){
					tc.add(steps);
				}
			}
		} else if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("xlsx")) {
			workBook = WorkbookFactory.create(fis);
			for (int i=0; i < numSheets; i++) {
				XSSFSheet sheet = (XSSFSheet) workBook.getSheetAt(i);
				steps = readFile(sheet);
				if (!steps.isEmpty()){
					tc.add(steps);
				}
			}
		} else if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("csv")) {
			List<List<String>> listofLines = new ArrayList<List<String>>();
			String csvFile = filePath + file.getName();
			scanner = new Scanner(new File(csvFile));
	        while (scanner.hasNext()) {
	            List<String> line = parseLine(scanner.nextLine());
	            listofLines.add(line);
	        }
	        scanner.close();
	        tc.add(listofLines);
		} else { 
			throw new IllegalArgumentException("Received file does not have a standard excel extension."); 
		}
		workBook.close();
		return tc;
	}
	
	public List<List<String>> readFile(XSSFSheet sheet){
		Iterator<Row> rowIterator = sheet.iterator();
		return read(rowIterator);
	}

	public List<List<String>> readFile(HSSFSheet sheet){
		Iterator<Row> rowIterator = sheet.iterator();
		return read(rowIterator);
	}
	
	public List<List<String>> read(Iterator<Row> rowIterator) {
		List<List<String>> listOfSteps = new ArrayList<>();
		List<String> step = null;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
//			Iterator<Cell> cellIterator = row.cellIterator();
			step = new ArrayList<String>();
			int maxNumCell = row.getLastCellNum();
			for (int i=0; i < maxNumCell ;i++) {
				Cell cell = row.getCell(i);				
				if (cell == null) {
					step.add(null);
				} else {
					step.add(cell.toString());
				}
			}
//			while(cellIterator.hasNext()) {
//				Cell cell = cellIterator.next();
//				if (cell.getCellTypeEnum() == CellType.BLANK) {
//					step.add(" ");
//					System.out.println("blank");
//				} else {
//					step.add(cell.toString());
//				}
//				
//			}
			listOfSteps.add(step);
		}
//		testcase = listOfSteps.get(0).toString();
//		listOfSteps.remove(0);
//		listOfSteps.remove(0);
		System.out.println(listOfSteps);
		return listOfSteps;
	}
	
	public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR);
    }
	
	 public static List<String> parseLine(String cvsLine, char separators) {

	        List<String> result = new ArrayList<>();

	        //if empty, return!
	        if (cvsLine == null && cvsLine.isEmpty()) {
	            return result;
	        }

	        if (separators == ' ') {
	            separators = DEFAULT_SEPARATOR;
	        }

	        StringBuffer curVal = new StringBuffer();
	        boolean startCollectChar = false;

	        char[] chars = cvsLine.toCharArray();

	        for (char ch : chars) {
	        	if (ch == separators) {
	        		result.add(curVal.toString());
	        		curVal = new StringBuffer();
	        		startCollectChar = false;
	        	} else if (ch == '\r') {
	        		//ignore LF characters
	        		continue;
	        	} else if (ch == '\n') {
	        		//the end, break!
	        		break;
	        	} else {
	        		curVal.append(ch);
	        	}
	        }
	        result.add(curVal.toString());
	        return result;
	    }
}
