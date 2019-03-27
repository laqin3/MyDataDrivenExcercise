import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DaysData2 {

//	public ArrayList<String> getData(String dataArgument) throws IOException {
	public static void main(String[] args) throws IOException {

		

		FileInputStream fi = new FileInputStream("C:\\Users\\laqin3\\Desktop\\dataDriven\\myPractice.xlsx");
		Workbook book = WorkbookFactory.create(fi);
		Sheet sheet = book.getSheet("datasheet");

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				System.out.println(data[i][j]);
				}
		
		}
	
		//read Data3 -Thursday: output should be frog
		Object[][] data1=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		Iterator<Row> rows=sheet.iterator();

		Row fr=rows.next();
		Iterator<Cell> frc=fr.cellIterator();
		int daysColumn=0;
		int k=0;
		while(frc.hasNext())
		{
			k++;
			if(frc.next().getStringCellValue().equalsIgnoreCase("Days")) {
				daysColumn=k;
			}
		}
		System.out.println("dayscolumn is: "+daysColumn);
		
		int e=0;
		int Data3RowNum=0;
		while(rows.hasNext()) {
			e++;
			if(rows.next().getCell(daysColumn).getStringCellValue().equalsIgnoreCase("Data3")) {
				Data3RowNum=e;
			}
		}
		System.out.println("data3 row number is: "+Data3RowNum);
		
		System.out.println(data1[3][Integer.parseInt("Thursday")]);
	}
	
	


}
