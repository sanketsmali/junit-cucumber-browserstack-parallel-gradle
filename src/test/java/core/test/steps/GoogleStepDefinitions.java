package core.test.steps;

import core.test.pages.pages.SearchPage;
import core.test.pages.pages.SearchResultPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class GoogleStepDefinitions {

  private SearchPage searchPage = new SearchPage();
  private SearchResultPage searchResultPage = new SearchResultPage();

  @Given("User open google.com")
  public void userOpenGoogleCom() throws InterruptedException {
    searchPage.openPage();
  }

  @When("User search {string}")
  public void userSearch(String keyword) {
    searchPage.search(keyword);
  }

  @When("User select first search result")
  public void userSelectFirstSearchResult() {
    searchResultPage.clickFirstSearchResult();
  }
}
