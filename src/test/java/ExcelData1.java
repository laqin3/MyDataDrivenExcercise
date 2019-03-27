import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData1 {

	public ArrayList<String> getData(String Argument) throws IOException {
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fi = new FileInputStream("C:\\Users\\laqin3\\Desktop\\dataDriven\\TomasAndSam.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fi);

		int sn = wb.getNumberOfSheets();
		for (int i = 0; i < sn; i++) {
			if (wb.getSheetName(i).equalsIgnoreCase("data")) {
				XSSFSheet ws = wb.getSheetAt(i);

				Iterator<Row> rows = ws.iterator();

				Row fr = rows.next();

				Iterator<Cell> frc = fr.iterator();
				int k = 0;
				int column = 0;
				while (frc.hasNext()) {
					// Cell fc = frc.next();
					if (frc.next().getStringCellValue().equalsIgnoreCase("first_name")) {
						column = k;
					}
					k++;
				}
				System.out.println(column);

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(Argument))
					{
						Iterator<Cell> drc = r.iterator();
						while (drc.hasNext()) {
							Cell dc = drc.next();
							if (dc.getCellTypeEnum() == CellType.STRING) {
								a.add(dc.getStringCellValue());
							} else {
								a.add(NumberToTextConverter.toText(dc.getNumericCellValue()));
							}
						}
						//System.out.println(a);

					}
				}

			}
		}
		return a;
	}
	public static void main(String[] args) throws IOException {
		ExcelData1 dd=new ExcelData1();
		System.out.println(dd.getData("Tomas"));
	}

}
