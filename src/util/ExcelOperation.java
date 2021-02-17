package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperation {
	
	public static String[][] getExcelData(String excelFileName, String sheetName) throws IOException {
		File file = new File("./resources/testdata/"+excelFileName);
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet(sheetName);
		int totalRow = sheet.getLastRowNum();
		int totalCol = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[totalRow][totalCol];
		for(int rowIndex = 1; rowIndex <= totalRow; rowIndex++) {
			for(int colIndex = 1; colIndex <= totalCol; colIndex++) {
				Cell cell = sheet.getRow(rowIndex).getCell(colIndex-1);
				if(cell.getCellType() == CellType.BOOLEAN) {
					data[rowIndex-1][colIndex-1] = String.valueOf(cell.getBooleanCellValue());
				}else if(cell.getCellType() == CellType.NUMERIC){
					data[rowIndex-1][colIndex-1] = String.valueOf((int)cell.getNumericCellValue());
				}else if(cell.getCellType() == CellType.BLANK) {
					data[rowIndex-1][colIndex-1] = "";
				}else if(cell.getCellType() == CellType.STRING) {
					data[rowIndex-1][colIndex-1] = cell.getStringCellValue();
				}
				System.out.println(data[rowIndex-1][colIndex-1]);
			}
		}
		wb.close();
		return data;
	}
}
