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

public class MyFamilyDataDriven {
	
	
	public ArrayList<String> myData(String familyMember) throws IOException {
		
		
		ArrayList<String> mylist = new ArrayList<String>();

		FileInputStream file = new FileInputStream("C:\\Users\\laqin3\\Desktop\\myfamilydatadriven.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("myfamily")) {
				XSSFSheet mySheet = workbook.getSheetAt(i);

				Iterator<Row> myRows = mySheet.iterator();

				Row firstRow = myRows.next();

				Iterator<Cell> items = firstRow.iterator();

				int k = 0;
				int column = 0;
				while (items.hasNext()) {
					if (items.next().getStringCellValue().equalsIgnoreCase("Family members")) {
						column = k;
					}
					k++;
				}
				System.out.println(column);

				while (myRows.hasNext()) {
					Row r = myRows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(familyMember)) {
						Iterator<Cell> ce = r.iterator();
						while (ce.hasNext()) {
							Cell c = ce.next();
							if (c.getCellTypeEnum() == CellType.STRING) {
								mylist.add(c.getStringCellValue());
							} else {
								mylist.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
						//System.out.println(mylist);
					}
				}
			}
		}
		return mylist;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	}
}