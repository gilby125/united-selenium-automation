package automation.framework;

import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	// This method is to set the File path and to open the Excel file
	// Pass Excel Path and SheetName as Arguments to this method
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
	}

	// This method is to read the input test data from the Excel cell
	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		final DataFormatter df = new DataFormatter();
		String CellData = "";
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			CellData = df.formatCellValue(Cell);
		} catch (Exception e) {
			return "";
		}
		return CellData;

	}
}