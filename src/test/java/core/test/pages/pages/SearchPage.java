package core.test.pages.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import core.setup.Hooks;

public class SearchPage {

	public void openPage() throws InterruptedException {
		Hooks.getDriver().get("https://www.google.in");
		Thread.sleep(2000);

		try {
			Hooks.getDriver().findElement(By.xpath("(//button)[6]")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void search(String keyword) {
		WebElement inputSearch = Hooks.getDriver().findElement(By.name("q"));
		inputSearch.sendKeys(keyword + Keys.ENTER);
	}

}
