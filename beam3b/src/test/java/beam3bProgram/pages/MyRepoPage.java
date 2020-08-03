package beam3bProgram.pages;

import beam3bProgram.steps.Hook;
import beam3bProgram.util.ReusableLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class MyRepoPage extends Hook {
    private ReusableLibrary rs;


   /* @FindBy(how = How.XPATH, using = "//a[@class='btn btn-sm btn-with-count  tooltipped tooltipped-s']")
    private static WebElement btnStar;*/
   @FindBy(how = How.XPATH, using = "//*[@id='js-repo-pjax-container']/div[1]/nav/ul/li[1]/a/span[1]")
   private static WebElement txtCode;

    @FindBy(how = How.XPATH, using = "//*[@type='submit' and @class='btn btn-sm btn-with-count  js-toggler-target'  and contains(@data-ga-click,'Repository, click star button')]")
    private static WebElement btnStar;

    @FindBy(how = How.XPATH, using = "//*[@type='submit' and @class='btn btn-sm btn-with-count  js-toggler-target'  and contains(@data-ga-click,'Repository, click unstar button')]")
    private static WebElement btnUnstar;

    @FindBy(how = How.XPATH, using = "//a[@class='HeaderMenu-link no-underline mr-3']")
    private static WebElement lnkSignIn;

    @FindBy(how = How.CSS, using = "#login_field")
    private static WebElement txtUsername;

    @FindBy(how = How.CSS, using = "#password")
    private static WebElement txtPasswd;

    @FindBy(how = How.XPATH, using = "//input[@type='submit']")
    private static WebElement btnSignIn;

    @FindBy(how = How.XPATH, using = "(//a[@class='social-count js-social-count' and contains(@aria-label,'starred this repository')])[1]")
    private static WebElement noOfStars;

    @FindBy(how = How.XPATH, using = "//summary[@class='Header-link' and @aria-label='View profile and more']")
    private static WebElement linkProfileMenu;

    @FindBy(how = How.XPATH, using = "//*[@class='dropdown-item dropdown-signout']")
    private static WebElement linkSignOut;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Go to file') and @class='btn ml-2']")
    private static WebElement btnGoToFile;

    @FindBy(how = How.CSS, using = "#tree-finder-field")
    private static WebElement txtFileFinder;

    @FindBy(how = How.CSS, using = "#tree-browser li")
    private static List<WebElement> tblFileList;

    @FindBy(how = How.CSS, using = "//h3[contains(text(),'No matching files found.')]")
    private static WebElement txtNoMatch;

    //***********************************************//

    public MyRepoPage(){
        this.rs=new ReusableLibrary(driver);
        PageFactory.initElements(driver, this);
    }

    //***********************************************//

    public void waitFor(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickStar(String starAction) {
        int noOfStarsExp;
        waitFor(txtCode);
        String noOfStarsAttrBef = noOfStars.getAttribute("aria-label");
        int noOfStarsBef =Integer.parseInt(noOfStarsAttrBef.replaceAll("[^0-9]", ""));
        System.out.println("No of starts before: "+noOfStarsAttrBef);
        try{
            if(starAction.equalsIgnoreCase("star")){
                noOfStarsExp = noOfStarsBef +1;
                clickStarUnstar(noOfStarsAttrBef, btnStar, "Star has been clicked", noOfStarsExp, "This repo has already been starred");
            }else if (starAction.equalsIgnoreCase("unstar")){
                noOfStarsExp = noOfStarsBef -1;
                clickStarUnstar(noOfStarsAttrBef, btnUnstar, "Unstar has been clicked", noOfStarsExp, "This repo has already been unstarred");
            }
        }catch (Exception e){
            System.out.println("Star/Unstar exception found "+e);
        }
    }

    private void clickStarUnstar(String noOfStarsAttributeBefore, WebElement btnStar, String s, int i, String s2) throws InterruptedException {
        if (btnStar.isDisplayed()) {
            btnStar.click();
            System.out.println(s);
            Thread.sleep(3000);
            rs.pageSync(driver);
            assertStarCount(noOfStarsAttributeBefore, i);
        } else {
            System.out.println(s2);
        }
    }

    private void assertStarCount(String noOfStarsAttributeBefore, int noOfStarsExp) {
        String noOfStarsAttrAft = noOfStars.getAttribute("text");
        int noOfStarsAft = Integer.parseInt(noOfStarsAttrAft.replaceAll("[^0-9]", ""));
        rs.assertIntFields("No of Stars", noOfStarsExp, noOfStarsAft);
    }


    public void signIn(String userName, String passwd) {
        waitFor(lnkSignIn);
        lnkSignIn.click();
        waitFor(txtUsername);
        txtUsername.clear();
        txtUsername.sendKeys(userName);
        txtPasswd.clear();
        txtPasswd.sendKeys(passwd);
        btnSignIn.click();
        System.out.println("User has been signed in");
        rs.pageSync(driver);
    }

    public void signOut() {
        linkProfileMenu.click();
        linkSignOut.click();
        System.out.println("Sign out is done");
    }

    public void searchFile(String fileName, String occurrence) throws InterruptedException {
        int occ = Integer.parseInt(occurrence);
        System.out.println("File to search is: " + fileName);
        waitFor(btnGoToFile);
        btnGoToFile.click();
        rs.pageSync(driver);
        waitFor(txtFileFinder);
        txtFileFinder.sendKeys(fileName);
        Thread.sleep(1000);
        int noOfMatchedFiles = tblFileList.size();

        rs.assertIntFields("Count of " + fileName, occ, noOfMatchedFiles);
    }
}
