package united.tests;

import java.awt.Robot;

import org.openqa.selenium.WebDriver;

import automation.framework.App;
import automation.framework.ErrorMsg;
import web.pages.United_HomePage;
import web.pages.United_SearchResults;

public class Start {
	public static WebDriver driver;

	public void Execute() {
		try {
			int row = 1;
			String results = "";
			App app = new App();
			app.setExcelVariables(row);
			boolean checkIfDone = app.checkIfDone();
			while (checkIfDone){
				Robot robot = new Robot();
				robot.mouseMove(0,0);
				driver = app.openBrowser("http://united.com");
				United_HomePage.bookTravel(driver, app.getOriginAirport(), app.getDestinationAirport(), app.getDepartDate());
				results = results + United_SearchResults.searchResults(driver);
				row = row + 3;
				app.setExcelVariables(row);
				checkIfDone = app.checkIfDone();
				driver.close();
			}
			
			app.createDocument(results);
		} catch (Exception e) {
			ErrorMsg.showErrorMessage("Automation Failed.");
			System.exit(1);
		}
	}

}
