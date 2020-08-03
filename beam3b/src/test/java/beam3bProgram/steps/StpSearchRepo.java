package beam3bProgram.steps;

import beam3bProgram.pages.SearchRepoPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class StpSearchRepo {
private SearchRepoPage searchRepoPage;

public StpSearchRepo(){
    this.searchRepoPage = new SearchRepoPage();
}


    @Given("User launches the browser and enters desired URL")
    public void userLaunchesTheBrowserAndEntersDesiredURL() {
        System.out.println("Browser launched and URL entered");
    }


    @When("User enters {string} name to be searched")
    public void userEntersNameToBeSearched(String repoName) {
    searchRepoPage.searchRepo(repoName);
    }


    @And("User verifies the search output and matches with expected {string}")
    public void userVerifiesTheSearchOutputAndMatchesWithExpected(String noOfMatchesExp) {
        searchRepoPage.verifySearchRepoOutput(noOfMatchesExp);
    }

    @And("User clicks on the {string} link")
    public void userClicksOnTheLink(String repoName) {
    searchRepoPage.clickRepo(repoName);
    }


}
