import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    //Data Providers
    @DataProvider(name="InvalidLoginData")
    public static Object[][] getDataFromDataProviders(){
        return new Object[][] {
                {"invalid@mail.com", "invalidPassword"},
                {"demo@class.com", ""},
                {"", "te$t$tudent"},
                {"",""}
        };
    }

    public WebDriver driver;
    public WebDriverWait wait;

    public Wait<WebDriver> fluentWait;

    public String url = "https://qa.koel.app/";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Parameters({"BaseUrl"})
    @BeforeMethod
    public void launchBrowser(String BaseUrl){
        //Chrome Options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Manage Browser - wait for 10 seconds before failing/quitting.
        driver = new ChromeDriver(options);
        //Implicit Wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Explicit Wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Fluent Wait
        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
                //.ignoring(NoAlertPresentException.class);
        driver.manage().window().maximize();
        //String url = BaseUrl;
        //Navigate to Url
        navigateToUrl(BaseUrl);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    void provideEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        //WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    void providePassword(String password) {
        //WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    void clickSubmit(){
        //WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        submitButton.click();
    }

    //Helper Method
    public void navigateToUrl(String givenUrl){
        driver.get(givenUrl);
    }
}