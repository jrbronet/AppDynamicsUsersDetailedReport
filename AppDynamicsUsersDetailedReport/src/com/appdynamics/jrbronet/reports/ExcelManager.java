package com.appdynamics.jrbronet.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	private File excelFile = null;
	private XSSFWorkbook book = null;
	FileInputStream fsIP = null;
	
	public void  buildReport(String fileName, List users, List roles, List groups){
		try{
			HSSFWorkbook wb = new HSSFWorkbook();
		    FileOutputStream fileOut = new FileOutputStream(fileName);
		    wb.write(fileOut);
		    fileOut.close();
			
			FileInputStream file = new FileInputStream(new File(fileName));	             
			//Get the workbook instance for XLS file 
			HSSFWorkbook workbook = new HSSFWorkbook(file);		
			
			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.createSheet("Users");
			int rowIdx = 0;				
			HSSFRow row = sheet.createRow(rowIdx++);				 				
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("Name");
			cell = row.createCell(1);				
			cell.setCellValue("ID");
			cell = row.createCell(2);				
			cell.setCellValue("Display Name");
			cell = row.createCell(3);
			cell.setCellValue("email");		
			cell = row.createCell(4);
			cell.setCellValue("Role");
			
			Iterator it = users.iterator();
		    while (it.hasNext()) {
		    	//Map.Entry pairs = (Map.Entry)it.next();
		    	User u = (User)it.next();
		        //System.out.println(pairs.getKey() + " = " + ((User)(pairs.getValue())).print());
		    	row = sheet.createRow(rowIdx++);				 				
				cell = row.createCell(0);
				cell.setCellValue(u.name);
				cell = row.createCell(1);
				cell.setCellValue(u.id);
				cell = row.createCell(2);				
				cell.setCellValue(u.display_name);
				cell = row.createCell(3);
				cell.setCellValue(u.email);		
				cell = row.createCell(4);
				cell.setCellValue(u.roleName);
		    }
				
				
				sheet = workbook.createSheet("Roles");
				rowIdx = 0;				
				row = sheet.createRow(rowIdx++);				 				
				cell = row.createCell(0);
				cell.setCellValue("Name");
				cell = row.createCell(1);				
				cell.setCellValue("Permission Name");
				cell = row.createCell(2);
				cell.setCellValue("Entity");
				cell = row.createCell(3);
				cell.setCellValue("Entity ID");
				
				it = roles.iterator();
			    while (it.hasNext()) {
			    	//Map.Entry pairs = (Map.Entry)it.next();
			    	Role r = (Role)it.next();
			        //System.out.println(pairs.getKey() + " = " + ((User)(pairs.getValue())).print());
			    	row = sheet.createRow(rowIdx++);				 				
					cell = row.createCell(0);
					cell.setCellValue(r.name);
					cell = row.createCell(1);				
					cell.setCellValue(r.permissionName);
					cell = row.createCell(2);
					cell.setCellValue(r.permissioEntity);
					cell = row.createCell(3);
					cell.setCellValue(r.entity_id);
			    }
			    
			    sheet = workbook.createSheet("Groups");
				rowIdx = 0;				
				row = sheet.createRow(rowIdx++);				 				
				cell = row.createCell(0);
				cell.setCellValue("ID");
				cell = row.createCell(1);				
				cell.setCellValue("Name");
				cell = row.createCell(2);
				cell.setCellValue("Description");
				cell = row.createCell(3);
				cell.setCellValue("User");
				cell = row.createCell(4);
				cell.setCellValue("Role");
				
				it = groups.iterator();
			    while (it.hasNext()) {
			    	//Map.Entry pairs = (Map.Entry)it.next();
			    	Group g = (Group)it.next();
			        //System.out.println(pairs.getKey() + " = " + ((User)(pairs.getValue())).print());
			    	row = sheet.createRow(rowIdx++);				 				
					cell = row.createCell(0);
					cell.setCellValue(g.id);
					cell = row.createCell(1);				
					cell.setCellValue(g.name);
					cell = row.createCell(2);
					cell.setCellValue(g.description);
					cell = row.createCell(3);
					cell.setCellValue(g.userName);
					cell = row.createCell(4);
					cell.setCellValue(g.roleName);
			    }
				
				file.close();
			    FileOutputStream out = 
			        new FileOutputStream(new File(fileName));
			    workbook.write(out);
			    out.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	

	

}
