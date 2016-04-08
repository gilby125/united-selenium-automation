package web.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.framework.ErrorMsg;

public class United_HomePage {
	private static String bookTravelDiv = "//div[@id='tile-book-travel']";
	private static String originTxtBox = "//input[@id='Origin']";
	private static String destinationTxtBox = "//input[@id='Destination']";
	private static String bookTravelSubmitBtn = "//button[@id='flightBookingSubmit']";
	private static String departDateTxtBox = "//input[@id='DepartDate']";
	private static String awardTravelChkBox = "//input[@id='AwardTravel']";
	private static String nonStopChkBox = "//input[@id='NonStopOnly']";
	private static String oneWayRadio = "//input[@id='SearchTypeMain_oneWay']";


	public static void bookTravel(WebDriver driver, String departAirport, String arrivalAirport, String departDate) {
		try {
			driver.findElement(By.xpath(bookTravelDiv)).click();
			driver.findElement(By.xpath(oneWayRadio)).click();
			driver.findElement(By.xpath(originTxtBox)).sendKeys(departAirport);
			driver.findElement(By.xpath(destinationTxtBox)).sendKeys(arrivalAirport);
			driver.findElement(By.xpath(departDateTxtBox)).sendKeys(departDate + Keys.TAB);
			driver.findElement(By.xpath(awardTravelChkBox)).click();
			driver.findElement(By.xpath(nonStopChkBox)).click();
			driver.findElement(By.xpath(bookTravelSubmitBtn)).click();
			
		} catch (Exception e) {
			ErrorMsg.showErrorMessage("Failed at booking travel information.");
			System.exit(1);
		}
	}
}
