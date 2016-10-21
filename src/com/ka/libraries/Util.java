package com.ka.libraries;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class Util {
	
	private String filePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 50 * 1024;
	private File file ;
	private HttpServletRequest request;
	
	public Util(HttpServletRequest request, String filePath) {
		this.request = request;
		this.filePath = filePath;
	}
	
	public boolean uploadFile() {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//Maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		//Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\temp"));
		//Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		//Maximum file size to be uploaded.
		upload.setSizeMax( maxFileSize );
		boolean isUploaded = false;

		try { 
			//Parse the request to get file items.
			List<FileItem> fileItems = upload.parseRequest(request);
			// Process the uploaded file items
			Iterator<FileItem> i = fileItems.iterator();
			while ( i.hasNext () ) {
				FileItem fi = (FileItem)i.next();
				if ( !fi.isFormField () ) {
					// Get the uploaded file parameters
					String fileName = fi.getName();
					//Write the file
					if( fileName.lastIndexOf("\\") >= 0 ){
						file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
					} else {
						file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
					}
					fi.write( file ) ;
					isUploaded = true;
				}
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return isUploaded;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
