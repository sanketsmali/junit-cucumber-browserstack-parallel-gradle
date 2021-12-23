package core.test.pages.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import core.setup.Hooks;

public class SearchResultPage {

  public void clickFirstSearchResult() {
    WebElement firstResult = Hooks.getDriver()
        .findElement(By.xpath("(//h3)[1]"));
    firstResult.click();
  }

}
