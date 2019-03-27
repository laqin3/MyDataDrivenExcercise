import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class ParkingI {

	private static final String Timestamp = null;

	public static void main(String[] args) throws IOException {

		File file = new File("C:\\Users\\laqin3\\Desktop\\dataDriven\\parkingLot.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb1 = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb1.getSheetAt(0);

		Iterator<Row> rowIterator = sheet1.iterator();

		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();

				switch (nextCell.getCellType()) {
				case BOOLEAN:
					System.out.println(nextCell.getBooleanCellValue() + "\t\t");
					break;
				case STRING:
					System.out.println(nextCell.getStringCellValue() + "\t\t");
					break;
				case NUMERIC:
				//	 System.out.println(nextCell.getNumericCellValue() + "\t\t");
					
					XSSFCellStyle style = (XSSFCellStyle) nextCell.getCellStyle();
					if (DateUtil.isCellDateFormatted(nextCell)) {
						Date d = nextCell.getDateCellValue();
						SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						System.out.println(dateFormat.format(d) + "\t\t");
						break;
					/*		
						  } else if (Timestamp.equals("00:00")) { // if time is 00:00:00 you can assume
						  it is a date only value (but it could be // midnight) // In this case I'm
						  fine with the default Cell.toString method (returning // dd-MMM-yyyy in case
						  of a date value) System.out.println(nextCell.toString()+Timestamp);*/
						 
						

					}
					// System.out.println(nextCell.getDateCellValue()+"\t\t");

				}
			}
			System.out.println("");
		}
		fis.close();
		FileOutputStream out1_parkLot = new FileOutputStream("C:\\Users\\laqin3\\Desktop\\dataDriven\\out2_parkingLot.xlsx");
		wb1.write(out1_parkLot);
		System.out.println("out_parkLot is created");
		out1_parkLot.close();
	}

}
