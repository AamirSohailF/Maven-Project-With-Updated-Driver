package com.etrading.skillkart.generic;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExcelLibrary 
{
	public static Object[][] getAllExcelSheetData(String filePath, String sheetName)
	{
		Object[][] objArr=null;
		try
		{
			Sheet sheet = WorkbookFactory.create(new FileInputStream(filePath)).getSheet(sheetName);
			int rowsCount = sheet.getPhysicalNumberOfRows();//6
			
			objArr = new Object[rowsCount-1][];//5 Size should be equal to number of rows except header row
			
			for(int i=1,k=0;i<=rowsCount-1;i++,k++)//1//2//3//4//5
			{
				int cellsCount = sheet.getRow(i).getPhysicalNumberOfCells();
				
				objArr[k]=new Object[cellsCount];
				
				for(int j=0;j<=cellsCount-1;j++)
				{
					objArr[k][j]=sheet.getRow(i).getCell(j).toString();
				}
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		return objArr;
	}
	
	public static String getCellData(String filePath, String sheetName, int rowNumber, int cellNumber)
	{
		String data="";
		try
		{
			Sheet sheet = WorkbookFactory.create(new FileInputStream(filePath)).getSheet(sheetName);
			data = sheet.getRow(rowNumber).getCell(cellNumber).toString();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		return data;
	}
}
















