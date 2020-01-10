package tel_ran.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tel_ran.helpers.BoardsPageHelper;
import tel_ran.helpers.HomePageHelper;
import tel_ran.helpers.LoginPageHelper;

public class LoginPageTests extends TestBase{
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests(){
      homePage = PageFactory.initElements(driver,HomePageHelper.class);
      loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
      boardsPage = new BoardsPageHelper(driver);
    }


    @Test
    public void loginToTrelloPositive()  {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertTrue(boardsPage.verifyIfBoardsIconIsDisplayed());
        Assert.assertTrue(boardsPage
                .verifyIfPersonalBoardsHeaderIsDisplayed());
    }

    @Test
    public void loginIncorrectPassNegative() {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN,PASSWORD+"1");
        loginPage.waitPasswordError();
        Assert.assertTrue(loginPage.verifyIfPasswordErrorIsCorrect(),"Error message is not correct");

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
