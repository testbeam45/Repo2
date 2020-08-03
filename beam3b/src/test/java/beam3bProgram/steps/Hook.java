package beam3bProgram.steps;

import beam3bProgram.util.ReusableLibrary;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Hook {

    public static WebDriver driver;
    public static Properties prop;
    public static WebDriverWait wait;
    private static int PAGE_LOAD_TIMEOUT = 20;
    private static int IMPLICIT_WAIT = 20;
    public static String browserName;
    public static String os;

    public Hook() {
        try {

            prop = new Properties();
            FileInputStream fis = new FileInputStream("src\\test\\resources\\config\\config.properties");
            prop.load(fis);
            browserName = prop.getProperty("browser");
            os = prop.getProperty("os");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void configureDriver() {
        if (os.equalsIgnoreCase("windows")) {
            if (browserName.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\driver\\chromedriver.exe");
            } else if (browserName.equalsIgnoreCase("ie")) {
                System.setProperty("webdriver.ie.driver", "src\\test\\resources\\driver\\IEDriverServer.exe");
            } else if (browserName.equalsIgnoreCase("edge")) {
                System.setProperty("webdriver.edge.driver", "src\\test\\resources\\driver\\msedgedriver.exe");
            }
        } else if (os.equalsIgnoreCase("mac")) {
            if (browserName.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\driver\\chromedriverMac");
            }
        }

    }


    @Before(order = 1)
    public static void initialization() {
        if (browserName.equalsIgnoreCase("Chrome")) {
            configureDriver();
            ChromeOptions options = new ChromeOptions();
            if (prop.getProperty("chromeheadless").equalsIgnoreCase("true")) {
                options.addArguments("--headless");
                System.out.println("************ Driver mode is headless **********");
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("ie")) {
            configureDriver();
            InternetExplorerOptions ieOptions = new InternetExplorerOptions();
            ieOptions.ignoreZoomSettings();
            driver = new InternetExplorerDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            configureDriver();
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver(); // Note: for safari no exe is required Webdriver interface already has SafariDriver
        }
        initialiseDriver();
    }

    private static void initialiseDriver() {
        wait = new WebDriverWait(driver, 30);
        System.out.println("************ Driver is Initialized **********");
        System.out.println("Browser is: " + browserName);


        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
        wait = new WebDriverWait(driver, 30);
        ReusableLibrary rs=new ReusableLibrary(driver);
        rs.pageSync(driver);
        //System.out.println("****** Driver is :"+driver+"***********");
        //System.out.println("****** DONE ***********");

    }

    @After(order = 2)
    public void afterScenario(Scenario scenario) throws IOException {
        //String screenShotFileName =scenario.getName().replaceAll(" ","")+".png";
        if (scenario.isFailed()) {
            System.out.println(scenario + " has failed");
        }
    }

    @After(order = 1)
    public void tearDown() {
        driver.quit();
        closeIE();
        System.out.println("************ Driver instance has been closed **********");
    }

//    @After(order=1)
//    public void afterScenario(){
//        if (scenario.isFailed()) {
//            // Take a screenshot...
//            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
//        }
//    }

    public void closeIE() {
        String browser = prop.getProperty("browser");
        if ("ie".equalsIgnoreCase(browser)) {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
                Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
