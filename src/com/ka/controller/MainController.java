package com.ka.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.ka.libraries.Constants;
import com.ka.libraries.FileLibrary;
import com.ka.libraries.KeywordUtil;
import com.ka.libraries.PdfUtility;
import com.ka.libraries.Util;
import com.sun.prism.paint.Color;

@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;
	private String page;
	private String ipAddress = "";
//	private Map<String, List<Map<String, String[]>>> crossBrowserResult = new HashMap<String, List<Map<String, String[]>>>();
	private List<String> crossBrowserResult = new ArrayList<String>();
	private String[][] browsers = {
			{"Firefox", Constants.FIREFOX_DRIVER, Constants.BROWSER_PROPERTY_FIREFOX},
			{"Chrome", Constants.CHROME_DRIVER, Constants.BROWSER_PROPERTY_CHROME}};
	private PdfUtility pdfUtility;

	public void init( ){
		filePath = Constants.PATH_UPLOAD;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		page = "view/error.jsp";
		String action = request.getParameter("action");
		RequestDispatcher dispatcher = null;
		if(action.equals("execute")){
			boolean isMultipart;
			isMultipart = ServletFileUpload.isMultipartContent(request);			
			if( !isMultipart ){
				page = "view/error.jsp";
			} else {
				String project = request.getParameter("projectName");
				String qa = request.getParameter("qaName");
				ipAddress = request.getParameter("ipaddress");
				System.out.println(ipAddress);
				uploadFile(request, response);
				try {
					savePDF();
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
		}
		dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean isUploaded;
		Util fileUpload = new Util(request, filePath);
		isUploaded = fileUpload.uploadFile();
		File file = fileUpload.getFile();
		if (isUploaded) {
			page = "view/result.jsp";
			readUploadedFile(file);
		} else {
			page = "view/error.jsp";
		}
	}
	
	private void readUploadedFile(File file) throws IOException{
		for (int i = 0; i < browsers.length; i++) {
			String[] browser = browsers[i];
			FileLibrary readFile = new FileLibrary(filePath, file);
			try {
				List<List<List<String>>> testSuite = readFile.create();
				for (List<List<String>> testCase : testSuite){
//					List<Map<String, String[]>> testCaseResult = new ArrayList<Map<String, String[]>>();
					String testCaseResult = processSteps(testCase, browser);
					crossBrowserResult.add(testCaseResult);
				}
				
				
			} catch (EncryptedDocumentException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private String processSteps(List<List<String>> testCase, String[] browser) throws IOException {
		String testCaseResult = "";
		for (List<String> steps : testCase) {
			String stepResult = "";
			int stepSize = steps.size();
			KeywordUtil ku;
			switch (stepSize) {
			case 1:
				ku = new KeywordUtil(steps.get(0));
				ku.setBrowser(browser);
				ku.setIpAddress(ipAddress);
				stepResult = ku.callMethod();
				break;
			case 2:
				ku = new KeywordUtil(steps.get(0), steps.get(1));
				stepResult = ku.callMethod();
				break;
			case 3:
				ku = new KeywordUtil(steps.get(0), steps.get(1), steps.get(2));
				stepResult = ku.callMethod();
				break;
			case 4:
				ku = new KeywordUtil(steps.get(0), steps.get(1), steps.get(2), steps.get(3));
				stepResult = ku.callMethod();
				break;
			default:
				//error
				break;
			}
			
			testCaseResult = testCaseResult.concat(stepResult + ",");
		}
		
		return "BROWSER: " + browser[0] + "\n" + testCaseResult;
	}
	
	protected void savePDF() throws IOException, DocumentException {
		pdfUtility = new PdfUtility();
		try {
			pdfUtility.WriteTestResultToPdfFile("Test.pdf", crossBrowserResult);
		} catch (COSVisitorException e) {
			e.printStackTrace();
		}
		
		
		String File = "C:\\Users\\rochelle\\Documents\\Workspace\\SeleniumTraining\\WebTest\\Test.pdf";
		Document document = new Document(PageSize.A4, 36, 36, 120, 54);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(File));
		document.open();
		//header
		Rectangle headerBox = new Rectangle(36, 54, 559, 788);
		HeaderFooter event = new HeaderFooter();
		writer.setBoxSize("headerBox", headerBox);
		writer.setPageEvent(event);
		
		Paragraph title1 = new Paragraph("Sample Test",FontFactory.getFont(FontFactory.HELVETICA,18, Font.BOLDITALIC, new CMYKColor(0, 255, 255,17)));
		Chapter chapter1 = new Chapter(title1, 1);
		chapter1.setNumberDepth(0);
		
		Paragraph title11 = new Paragraph("Sample Section",FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,new CMYKColor(0, 255, 255,17)));
		Section section1 = chapter1.addSection(title11);
		Paragraph someSectionText = new Paragraph("This text comes as part of section 1 of chapter 1.");
		section1.add(someSectionText);

		
		//document.add(new Paragraph(message));
	    
		for(String finalResult : crossBrowserResult){
			PdfPTable t = new PdfPTable(3);
			t.setSpacingBefore(15);
		    t.setSpacingAfter(25);
		       
		    PdfPCell c1 = new PdfPCell(new Phrase("STEPS"));  
		    t.addCell(c1);
		    PdfPCell c2 = new PdfPCell(new Phrase("RESULT"));
		    t.addCell(c2);
		    PdfPCell c3 = new PdfPCell(new Phrase("REMARKS"));
		    t.addCell(c3);
			String[] lines = finalResult.split(",");
			for(String line: lines){
				String[] cells = line.split("-");
				
				
				
				if (cells.length == 3) {
					for(int i=0; i < 3; i++){
						t.addCell(cells[i]);   
					}
				} else {
					for(int i=0; i < cells.length; i++){
						t.addCell(cells[i]);   
					}
					for(int i=0; i < 3 - cells.length; i++){
						t.addCell(" ");   
					}
				}
					
			}
			section1.add(t);
		}
		
		
		document.add(chapter1);
		document.close();  

	}
	
	static class HeaderFooter extends PdfPageEventHelper {

		  public void onEndPage(PdfWriter writer, Document document) {
		    Rectangle rect = writer.getBoxSize("headerBox");
		    // add header text
		   // ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_RIGHT, new Phrase("Hello"),rect.getLeft(), rect.getTop(), 0);

		    // add header image
		    try {
		      Image img = Image.getInstance("C:\\Users\\rochelle\\Documents\\Workspace\\SeleniumTraining\\WebTest\\WebContent\\imgs\\cpi_logo.png");
		      img.scaleToFit(100,100);
		      img.setAbsolutePosition(35,742); 
		      writer.getDirectContent().addImage(img);
		      //document.add(img);
		    } catch (Exception x) {
		      x.printStackTrace();
		    }

		  }

		}


}
