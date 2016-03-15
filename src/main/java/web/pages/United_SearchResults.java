package web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.framework.ErrorMsg;

public class United_SearchResults {

	private static String departTime = "//div[@class='flight-time flight-time-depart']";
	private static String arrivalTime = "//div[@class='flight-time flight-time-arrive']";

	public static String searchResults(WebDriver driver) {
		String output = "";
		WebElement saverAwardMiles;
		WebElement saverAwardLabel;
		try {
			List<WebElement> departTimeList = driver.findElements(By.xpath(departTime));
			List<WebElement> arrivalTimeList = driver.findElements(By.xpath(arrivalTime));
			for (int i = 0; i < departTimeList.size(); i++) {
				int x = i + 1;
				try {
					saverAwardMiles = driver.findElement(
							By.xpath(".//*[@id='fl-results']/div/ul/li[" + x + "]/div[2]/div[1]/div[1]/div[1]/div[2]"));
					saverAwardLabel = driver.findElement(
							By.xpath(".//*[@id='fl-results']/div/ul/li[" + x + "]/div[2]/div[1]/div[1]/div[1]/div[1]"));
				} catch (Exception e) {
					x++;
					saverAwardMiles = driver.findElement(
							By.xpath(".//*[@id='fl-results']/div/ul/li[" + x + "]/div[2]/div[1]/div[1]/div[1]/div[2]"));
					saverAwardLabel = driver.findElement(
							By.xpath(".//*[@id='fl-results']/div/ul/li[" + x + "]/div[2]/div[1]/div[1]/div[1]/div[1]"));
				}

				if (saverAwardLabel.getText().equals("Saver Award")) {
					String saverAwardFormatted = saverAwardMiles.getText().replaceAll("(\\r|\\n)", " ");
					String departTimeListFormatted = departTimeList.get(i).getText().replaceAll("(\\r|\\n)", " ")
							+ "\n";
					String arrivaltimeListFormatted = arrivalTimeList.get(i).getText().replaceAll("(\\r|\\n)", " ")
							+ "\n";
					output = output + departTimeListFormatted + arrivaltimeListFormatted + saverAwardFormatted
							+ "\n------------------------------------------------------------------------\n";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			ErrorMsg.showErrorMessage("Failed at search results.");
			System.exit(1);
		}
		return output;
	}
}
