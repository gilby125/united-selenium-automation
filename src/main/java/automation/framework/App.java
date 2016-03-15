package automation.framework;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import web.pages.United_HomePage;

public class App {
	public WebDriver driver;
	private String excelFile = (System.getProperty("user.dir") + "/United.xlsx").replace("\\", "/");
	private String excelSheetName = "Sheet1";
	private String originAirport;
	private String destinationAirport;
	private String departDate;

	public App() {
		//Display a message box showing the user that the script is running
		showMessageBox();
	}

	//Sets the variables from the excel input file
	public void setExcelVariables(int startRow) {
		try {
			ExcelUtils.setExcelFile(excelFile, excelSheetName);
			setOriginAirport(startRow);
			setDestinationAirport(startRow);
			setDepartDate(startRow);
		} catch (Exception e) {
			ErrorMsg.showErrorMessage("Error with Excel File.");
			e.printStackTrace();
			System.exit(1);
		}
	}
	//Check to see if end of excel file
	public Boolean checkIfDone() {
		if (departDate != "") {
			return true;
		} else {
			return false;
		}
	}

	private void showMessageBox() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				JOptionPane.showOptionDialog(null, "Automation Script is Running", "In Progress...",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
			}
		});
		t.start();
	}

	// opens and maximizes browser
	public WebDriver openBrowser(String url) {
		driver = new FirefoxDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	private void setOriginAirport(int row) throws Exception {
		originAirport = ExcelUtils.getCellData(row+1, 1, excelSheetName);
	}

	public String getOriginAirport() {
		return originAirport;
	}

	private void setDestinationAirport(int row) throws Exception {
		destinationAirport = ExcelUtils.getCellData(row+2, 1, excelSheetName);
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	private void setDepartDate(int row) throws Exception {
		departDate = ExcelUtils.getCellData(row, 1, excelSheetName);
	}

	public String getDepartDate() {
		return departDate;
	}

	//Creates the results document and displays the results
	public void createDocument(String output) {
		try {
			String results = (System.getProperty("user.dir") + "/results.docx").replace("\\", "/");
			String outputDirectory = (results);
			XWPFDocument document = new XWPFDocument();
			XWPFParagraph tmpParagraph = document.createParagraph();
			XWPFRun tmpRun = tmpParagraph.createRun();
			tmpRun.setText(output);
			tmpRun.setFontSize(18);
			document.write(new FileOutputStream(new File(outputDirectory)));
			document.close();
			//Open File to view
			File file =  new File(results);
			Desktop dt = Desktop.getDesktop();
		    dt.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
