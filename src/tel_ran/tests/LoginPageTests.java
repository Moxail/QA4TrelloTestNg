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
        //Thread.sleep(5000);
        loginIcon.click();
        waitUntilElementIsClickable(By.id("login"),30);
        WebElement userField = driver.findElement(By.id("user"));
        userField.click();
        userField.clear();
        userField.sendKeys("marinaqatest2019@gmail.com");
        driver.findElement(By.id("login")).click();
        //Thread.sleep(5000);
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("login-submit")).click();
        //Thread.sleep(3000);
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("password")).sendKeys("marinaqa");
        driver.findElement(By.id("login-submit")).click();
        //Thread.sleep(30000);
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
        //Thread.sleep(5000);
        driver.findElement(By.id("login-submit")).click();
        //Thread.sleep(3000);
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("password")).sendKeys("marinaqaa");
        driver.findElement(By.id("login-submit")).click();
        //Thread.sleep(10000);
        waitUntilElementIsVisible(By
                .xpath("//div[@id = 'login-error']/span"),10);
        //System.out.println("Error message: " + driver.findElement(By.xpath("//div[@id = 'login-error']/span")).getText());
        Assert.assertTrue(driver.findElement(By
                .xpath("//div[@id = 'login-error']/span")).getText()
                .contains("Incorrect email address and / or password."));

    }


}
