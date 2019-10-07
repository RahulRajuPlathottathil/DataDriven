package baseTest;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String FilePath;
	public FileInputStream fis;
	public FileOutputStream fout;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;

	public static void main(String[] args) {
		ExcelReader excel = new ExcelReader("C:\\Users\\Lenovo\\Desktop\\data\\DataSheet.xlsx");
		System.out.println(excel.getCellData("Driver", "TESTCASE NAME", 2));
	}

	public ExcelReader(String FilePath) {

		this.FilePath = FilePath;
		try {
			fis = new FileInputStream(FilePath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ------------------------------------------------

	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0) {
				return "";
			}
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return "";
			}

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);

			if (row == null) {
				return "";
			}

			cell = row.getCell(colNum);
			if (cell == null) {
				return "";
			}
			if (cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC) {
				String data = String.valueOf(cell.getNumericCellValue());
				return data;
			} else if (cell.getCellType() == CellType.BLANK) {
				String data = "";
				return data;
			} else {
				String data = String.valueOf(cell.getBooleanCellValue());
				return data;
			}

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist in xlsx";
		}

	}

	// Sheet Name ,ColName ,rowno

	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0) {
				return "";
			}
			int colNum = -1;
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return "";
			}

			sheet = workbook.getSheetAt(index);
			Row row = sheet.getRow(0);

			for (int i = 0; i < row.getLastCellNum(); i++) {
				String header = row.getCell(i).getStringCellValue().trim();
				if (header.equalsIgnoreCase(colName)) {
					colNum = i;
					break;
				}
			}
			if (colNum == -1) {
				return "";
			}

			String data = getCellData(sheetName, colNum, rowNum);
			return data;

		} catch (Exception e) {
			e.printStackTrace();
			return "Data not found at " + colName + " and" + rowNum;
		}

	}
	// returns the row count in a sheet
		public int getRowCount(String sheetName){
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return 0;
			else{
			sheet = workbook.getSheetAt(index);
			int number=sheet.getLastRowNum()+1;
			return number;
			}
			
		}
		
		// returns number of columns in a sheet	
		public int getColumnCount(String sheetName){
			// check if sheet exists
			if(!isSheetExist(sheetName))
			 return -1;
			
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			
			if(row==null)
				return -1;
			
			return row.getLastCellNum();
			
			
			
		}
		// find whether sheets exists	
		public boolean isSheetExist(String sheetName){
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1){
				index=workbook.getSheetIndex(sheetName.toUpperCase());
					if(index==-1)
						return false;
					else
						return true;
			}
			else
				return true;
		}

	// ------------------------------------------------
}
