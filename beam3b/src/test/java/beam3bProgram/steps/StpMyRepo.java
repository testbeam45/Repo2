package beam3bProgram.steps;

import beam3bProgram.pages.MyRepoPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;


public class StpMyRepo {
    private MyRepoPage myRepoPage;

    public StpMyRepo(){
        myRepoPage=new MyRepoPage();
    }

    @And("User signs in with {string} and {string}")
    public void userSignsInWithAnd(String userName, String passwd) {
        myRepoPage.signIn(userName, passwd);
    }


    @And("User clicks {string} and verifies star count")
    public void userClicksAndVerifiesStarCount(String starAction) {
        myRepoPage.clickStar(starAction);
    }


    @And("User signs out")
    public void userSignsOut() {
        myRepoPage.signOut();
    }


    @And("User searches for the file {string} and its {string} in the repo")
    public void userSearchesForTheFileAndItsInTheRepo(String fileName, String occurrence) throws InterruptedException {
        myRepoPage.searchFile(fileName, occurrence);
    }
}
