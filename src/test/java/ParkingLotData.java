import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParkingLotData {

	public static void main(String[] args) throws IOException {

		File file=new File("C:\\Users\\laqin3\\Desktop\\dataDriven\\parkingLot.xlsx");
		FileInputStream fis =new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		
		XSSFSheet sheet1=wb.getSheet("Sheet1");
		
		String cellvaluefirst=sheet1.getRow(1).getCell(0).getStringCellValue();
		System.out.println(cellvaluefirst);
		
		System.out.println("******************");
		
		int rowSize=sheet1.getLastRowNum();
		int columnSize=sheet1.getRow(rowSize).getLastCellNum();
		System.out.println("row size is "+rowSize+", "+"column size is "+columnSize);
		Object[][] obj=new Object[sheet1.getLastRowNum()][sheet1.getRow(0).getLastCellNum()];
		for (int i = 0; i < rowSize; i++) {
			
			for(int j=0;j<columnSize;j++) {
				obj[i][j]=sheet1.getRow(i).getCell(j).toString();
				System.out.println(obj[i][j]);
				
			}
			System.out.println("*********");
		}
	}

}
