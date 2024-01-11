import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

   /* @Parameters({"BaseUrl"})
    @Test
    public void navigateToKoelApp(String BaseUrl) {
        navigateToUrl(BaseUrl);
        Assert.assertEquals(driver.getCurrentUrl(), BaseUrl);
        driver.quit();
    }*/

    @Test
    public void loginValidEmailPassword() throws InterruptedException{
        //try{
            provideEmail("demo@class.com");
            providePassword("te$t$tudent");
            clickSubmit();
            //Thread.sleep(2000);
            //Assertion
            WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
            //WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[class='avatar']")));
            Assert.assertTrue(avatarIcon.isDisplayed());
        //} catch (Exception e){
           // System.out.println("Something went wrong." +e);
        //}
    }

    @Test(dataProvider = "InvalidLoginData", dataProviderClass=BaseTest.class)
    //@Parameters({"BaseUrl"})
    public void loginWithInvalidEmailValidPassword(String email, String password) throws InterruptedException {
        provideEmail(email);
        //Password Field
        providePassword(password);
        //Submit button
        clickSubmit();
        //Thread.sleep(2000);
        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    /*@Test
    public void loginWithInvalidPasswordAndValidEmail() throws InterruptedException {
        //navigateToUrl();
        //Email Field
        provideEmail("demo@class.com");
        //Password Field
        providePassword("invalidPassword");
        //Submit button
        clickSubmit();
        Thread.sleep(2000);
        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }*/


}




