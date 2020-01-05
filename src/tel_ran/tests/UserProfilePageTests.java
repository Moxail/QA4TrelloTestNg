package tel_ran.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class UserProfilePageTests extends TestBase {
    @BeforeMethod
    public void initTest(){
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
        //----Open 'QA 4 Auto' board
        waitUntilElementIsVisible(By.xpath("//div[@title='QA4 Auto']/.."),20);
        driver.findElement(By.xpath("//div[@title='QA4 Auto']/..")).click();

        //----Open 'User Profile' page ----
        driver.findElement(By.xpath("//button[@data-test-id = 'header-member-menu-button']")).click();
        waitUntilElementIsClickable(By.xpath("//span[contains(text(),'Profile and Visibility')]"),10);
        driver.findElement(By.xpath("//span[contains(text(),'Profile and Visibility')]")).click();

        waitUntilElementIsClickable(By.xpath("//input[@name='initials']"),30);



    }

    @Test
    public void initialsVerification(){
        String initials = driver.findElement(By.xpath("//input[@name='initials']")).getAttribute("value");

        List<WebElement> listInitialsToVerify = driver
                .findElements(By.xpath("//div[@title='marinaqatest (marinaqatest)']/span"));
        int counter=0;
        if (listInitialsToVerify.size()==2) counter++;
        if (listInitialsToVerify.get(0).getText().equals(initials)) counter++;
        if (listInitialsToVerify.get(1).getText().equals(initials)) counter++;

        Assert.assertEquals(counter,3, "Not all elements contains correct initials. It should be two elements");


    }

}
