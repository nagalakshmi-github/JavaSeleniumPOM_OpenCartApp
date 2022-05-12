package session2.dataProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class WriteDataToExcelFileUsingApachePOI {
	// any exceptions need to be caught
	@Test
	public void Write() throws Exception {
		// workbook object
		XSSFWorkbook workbook = new XSSFWorkbook();
		// spreadsheet object
		XSSFSheet spreadsheet = workbook.createSheet(" Employee Data ");
		// creating a row object
		XSSFRow row;
		// This data needs to be written (Object[])
		Map<String, Object[]> studentData = new TreeMap<String, Object[]>();

		studentData.put("1", new Object[] { "Employee Id", "NAME", "POST" });

		studentData.put("2", new Object[] { "1301", "Prajjawal", "Intern" });
		studentData.put("3", new Object[] { "1302", "Nitish", "Software Consultant" });
		studentData.put("4", new Object[] { "1303", "Aditi", "QA Engineer" });
		studentData.put("5", new Object[] { "1303", "Ayush", "System Engineer" });
		studentData.put("6", new Object[] { "1304", "Abhishek", "Intern" });

		Set<String> keyid = studentData.keySet();

		int rowid = 0;
		// writing the data into the sheets...
		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = studentData.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
				System.out.println(cellid + " " + cell);
			}
		}
		// .xlsx is the format for Excel Sheets...
		// writing the workbook into the file...
		FileOutputStream out = new FileOutputStream(new File("src//test//java//Write.xlsx"));
		workbook.write(out);

		out.close();
	}
}