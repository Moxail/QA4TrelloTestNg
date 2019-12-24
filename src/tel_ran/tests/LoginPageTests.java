package tel_ran.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTests extends TestBase{


    @Test
    public void loginToTrelloPositive() throws InterruptedException {

        //----Login to trello----
        WebElement loginIcon = driver.findElement(By
                .xpath("//a[@class='btn btn-sm btn-link text-white']"));
        Thread.sleep(5000);
        loginIcon.click();
        WebElement userField = driver.findElement(By.id("user"));
        userField.click();
        userField.clear();
        userField.sendKeys("marinaqatest2019@gmail.com");
        driver.findElement(By.id("login")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys("marinaqa");
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(30000);
    }

    @Test
    public void loginIncorrectPassNegative() throws InterruptedException {
        //----Login to trello----
        WebElement loginIcon = driver.findElement(By
                .xpath("//a[@class='btn btn-sm btn-link text-white']"));
        Thread.sleep(5000);
        loginIcon.click();
        WebElement userField = driver.findElement(By.id("user"));
        userField.click();
        userField.clear();
        userField.sendKeys("marinaqatest2019@gmail.com");
        driver.findElement(By.id("login")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys("marinaqaa");
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(10000);
        System.out.println("Error message: " + driver.findElement(By.xpath("//div[@id = 'login-error']/span")).getText());

    }


}
