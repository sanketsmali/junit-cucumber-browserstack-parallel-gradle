package core.test.steps;

import org.openqa.selenium.By;

import core.setup.Hooks;



public class ArticlePage {

  public String getPokemonNumber() {
    return Hooks.getDriver().findElement(By.xpath("//big//abbr")).getText();
  }

  public String getPokemonName() {
    return Hooks.getDriver().findElement(By.id("firstHeading")).getText();
  }

}
