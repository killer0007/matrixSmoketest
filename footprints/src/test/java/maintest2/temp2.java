package maintest2;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class temp2 {

	public static void main(String[] args) throws Exception{
		String path="C:\\Users\\admin\\Downloads\\Sample-Sales-Data.xlsx";
		FileInputStream fis = new FileInputStream(new File(path));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheetAt(0);
		Row row = sheet.getRow(0);
		Cell cell= row.getCell(0);
		System.out.println(cell.getStringCellValue());
		wb.close();
	}

}
