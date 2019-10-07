package baseTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReporter {

	static Map<String, Object[]> Report = new HashMap<>();

	public static void ExcelReport(String TCNAME, String TCID, String SSN, String DOB, String FIRSTNAME,

			String LASTNAME, String STATUS) {

		report(TCNAME, new Object[] { TCID, SSN, DOB, FIRSTNAME, LASTNAME, LASTNAME, STATUS });
	}

	public static void report(String key, Object[] data) {
		Report.put(key, data);
	}

	public static void reportFlush() {
		XSSFWorkbook wokbook;
		int rowNo = 1;
		wokbook = new XSSFWorkbook();
		Date d = new Date();
		Set<String> set = Report.keySet();
		TreeSet<String> keyset = new TreeSet<String>(set);
		XSSFSheet sheet = wokbook.createSheet("TPR_REPORT");
		Row rowHeader = sheet.createRow(0);
		String[] HeaderArray = { "TESTNAME", "TEST ID", "SSN", "DOB", "TP FIRST NAME", "TP LAST NAME", "TEST STATUS" };
		for (int hedr = 0; hedr < HeaderArray.length; hedr++) {
			CellStyle style = wokbook.createCellStyle();
			Cell cell = rowHeader.createCell(hedr);
			cell.setCellValue(HeaderArray[hedr].toUpperCase());
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell.setCellStyle(style);
		}
		for (String key : keyset) {
			Row row = sheet.createRow(rowNo++);
			Object[] objArr = Report.get(key);
			int col = 0;
			for (Object obj : objArr) {
				System.out.println(obj);
				Cell cell = row.createCell(col++);
				if (obj instanceof String) {
					cell.setCellValue((String) obj);
					if (obj.equals("PASS")) {
						CellStyle style = wokbook.createCellStyle();
						style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
						style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						cell.setCellStyle(style);
					} else if (obj.equals("FAIL")) {
						CellStyle style = wokbook.createCellStyle();
						style.setFillForegroundColor(IndexedColors.RED.getIndex());
						style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						cell.setCellStyle(style);
					}
				} else if (obj instanceof Integer) {
					cell.setCellValue((Integer) obj);
				}
			}
		}
		try {
			FileOutputStream fout = new FileOutputStream(new File("C:\\Users\\Lenovo\\Desktop\\data\\trddf.xlsx"));
			wokbook.write(fout);
			fout.close();
			wokbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExcelReport("TC001", "123", "12344444", "2002121", "RAH", "RAJ", "PASS");
		ExcelReport("TC002", "123", "12344444", "2002121", "RAH", "RAJ", "FAIL");
		reportFlush();
	}
}