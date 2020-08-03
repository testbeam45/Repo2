package beam3bProgram.pages;

import beam3bProgram.steps.Hook;
import beam3bProgram.util.ReusableLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchRepoPage extends Hook {
    private ReusableLibrary rs;
    public String repoSearchResult;

    @FindBy(how = How.CSS, using = "#your-repos-filter")
    private static WebElement txtSearchRepo;

    @FindBy(how = How.XPATH, using = "//*[@id=\"org-repositories\"]/div[1]/div/div[1]/div[2]/a")
    private static WebElement lnkClearFilter;

    @FindBy(how = How.XPATH, using = "//*[@id='org-repositories']/div[1]/div/div[1]/div[1]")
    private static WebElement txtSearchOutTable;

    //    ***********************************************   //
    public SearchRepoPage(){
        this.rs=new ReusableLibrary(driver);
        PageFactory.initElements(driver, this);
    }
    //    ***********************************************   //

    public void waitFor(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void searchRepo(String repoName) {
        if (!repoName.equals("")) {
            rs.pageSync(driver);
            waitFor(txtSearchRepo);
            txtSearchRepo.clear();
            txtSearchRepo.sendKeys(repoName);
            txtSearchRepo.sendKeys(Keys.RETURN);
            rs.pageSync(driver);
            waitFor(lnkClearFilter);
            repoSearchResult=txtSearchOutTable.getText();
        }else{
            System.out.println("Repository search string is null; failing the test");
            Assert.fail();
        }
    }

    public void verifySearchRepoOutput(String noOfMatchesExp) {
        if (repoSearchResult.contains("results for repositories matching") && !repoSearchResult.contains("0 results for repositories matching")) {
            int noOfMatches = Integer.parseInt(repoSearchResult.replaceAll("[^0-9]", ""));
            System.out.println("Repository search result is: " + repoSearchResult);
            int expMatches = Integer.parseInt(noOfMatchesExp);
            rs.assertIntFields("No Of Repositories", expMatches, noOfMatches);
        } else {
            System.out.println("No repository found");
            Assert.fail();
        }
    }

    public void clickRepo(String repoName){
        WebElement repoElement = driver.findElement(By.xpath("(//a[contains(text(),'"+repoName+"') and @data-hovercard-type='repository'])[1]"));
        repoElement.click();
        System.out.println("Navigated to repo: "+repoName);
    }


}
