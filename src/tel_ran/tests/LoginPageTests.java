package tel_ran.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTests extends TestBase{


    @Test
    public void loginToTrelloPositive() throws InterruptedException {

        //----Login to trello----

        WebElement loginIcon = driver.findElement(By
                .xpath("//a[@class='btn btn-sm btn-link text-white']"));

        loginIcon.click();
        waitUntilElementIsClickable(By.id("login"),30);
        WebElement userField = driver.findElement(By.id("user"));
        userField.click();
        userField.clear();
        userField.sendKeys("marinaqatest2019@gmail.com");
        driver.findElement(By.id("login")).click();

        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("login-submit")).click();

        waitUntilElementIsClickable(By.id("password"),30);
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("password")).sendKeys("marinaqa");
        driver.findElement(By.id("login-submit")).click();

        waitUntilElementIsClickable(By
                .xpath("//button[@data-test-id='header-boards-menu-button']"),30);

        Assert.assertTrue(driver.findElement(By
                .xpath("//button[@data-test-id='header-boards-menu-button']"))
                .isDisplayed());
        Assert.assertTrue(driver.findElement(By
                .xpath("//h3[@class='boards-page-board-section-header-name']"))
                .getText().equals("Personal Boards"));
    }



    @Test
    public void loginIncorrectPassNegative() throws InterruptedException {
        //----Login to trello----
        WebElement loginIcon = driver.findElement(By
                .xpath("//a[@class='btn btn-sm btn-link text-white']"));
        loginIcon.click();
        waitUntilElementIsClickable(By.id("login"),30);
        WebElement userField = driver.findElement(By.id("user"));
        userField.click();
        userField.clear();
        userField.sendKeys("marinaqatest2019@gmail.com");
        driver.findElement(By.id("login")).click();
        waitUntilElementIsClickable(By.id("login-submit"),30);

        driver.findElement(By.id("login-submit")).click();

        waitUntilElementIsClickable(By.id("password"),30);
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("password")).sendKeys("marinaqaa");
        driver.findElement(By.id("login-submit")).click();

        waitUntilElementIsVisible(By
                .xpath("//div[@id = 'login-error']/span"),10);

        Assert.assertTrue(driver.findElement(By
                .xpath("//div[@id = 'login-error']/span")).getText()
                .contains("Incorrect email address and / or password."),"Error message is not correct");

    }
    @Test
    public void loginIncorrectLoginNegative(){
        WebElement loginIcon = driver.findElement(By
                .xpath("//a[@class='btn btn-sm btn-link text-white']"));
        loginIcon.click();
        //----- go to login page -------
        waitUntilElementIsClickable(By.xpath("//input[@id='login']"),30);
        WebElement loginField = driver.findElement(By.xpath("//input[@type='email']"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys("klmn");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("hjdhj");
        driver.findElement(By.id("login")).click();
        // ----- wait error message -----
        waitUntilElementIsVisible(By.xpath("//p[@class = 'error-message']"),20);
        System.out.println("Error text: " + driver
                .findElement(By.xpath("//p[@class = 'error-message']")).getText());
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class = 'error-message']"))
                .getText(), "Invalid password","'Invalid password' message is not displayed");




    }


}
