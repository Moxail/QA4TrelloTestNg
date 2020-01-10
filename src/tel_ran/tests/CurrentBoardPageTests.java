package tel_ran.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tel_ran.helpers.BoardsPageHelper;
import tel_ran.helpers.CurrentBoardPageHelper;
import tel_ran.helpers.HomePageHelper;
import tel_ran.helpers.LoginPageHelper;

import java.util.List;
import java.util.Random;

public class CurrentBoardPageTests extends TestBase{
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa4AutoBoard;

    @BeforeMethod
    public void initTest(){
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        qa4AutoBoard = new CurrentBoardPageHelper(driver, "QA4 Auto");

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
    }

    @Test
    public void verifyIfLoadedBoardIsCorrect()  {

        //----Open 'QA 4 Auto' board
        boardsPage.openBoard("QA4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
        Assert.assertTrue(qa4AutoBoard.titleVerification());
    }

    @Test
    public void createNewList()  {
        boardsPage.openBoard("QA4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
        String nameList = "New List";
        if (qa4AutoBoard.getAddButtonName().equals("Add another list")){
            nameList = qa4AutoBoard.genRandomString(7);
            if(qa4AutoBoard.existsList(nameList))
                nameList = qa4AutoBoard.stringWithRandomNumber(1000,
                        nameList);
        }
        int quantityListAtFirst = qa4AutoBoard.getQuantityLists();
        qa4AutoBoard.createNewList(nameList);
        int quantityListAtTheEnd = qa4AutoBoard.getQuantityLists();
        Assert.assertEquals(quantityListAtFirst+1,quantityListAtTheEnd);
        Assert.assertEquals(qa4AutoBoard.getAddButtonName(),
                "Add another list");
    }
    @Test
     public void deleteList(){
        //----Open 'QA 4 Auto' board
        waitUntilElementIsVisible(By.xpath("//div[@title='QA4 Auto']/.."),20);
        driver.findElement(By.xpath("//div[@title='QA4 Auto']/..")).click();
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),30);
        WebElement addButton = driver.findElement(By.cssSelector(".placeholder"));
        int quantityListBegining;
        if (addButton.getText().equals("Add a list")){
            addButton.click();
            waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),5);
            driver.findElement(By.xpath("//input[@name = 'name']")).sendKeys("New List");
            driver.findElement(By.xpath("//input[@type = 'submit']")).click();
            waitUntilElementIsClickable(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"),5);
            driver.findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']")).click();
            quantityListBegining=1;
        }
        else {
            waitUntilElementIsClickable(By.cssSelector(".js-open-list-menu"),10);
            quantityListBegining = driver.findElements(By.cssSelector(".js-open-list-menu")).size();

        }

        //---- delete list----------------
        waitUntilElementIsClickable(By.cssSelector(".js-open-list-menu"),10);
        driver.findElement(By.cssSelector(".js-open-list-menu")).click();
        waitUntilElementIsClickable(By.cssSelector(".js-close-list"),10);
        driver.findElement(By.cssSelector(".js-close-list")).click();
        int quantityListEnd = driver.findElements(By.cssSelector(".js-open-list-menu")).size();

        Assert.assertEquals(quantityListBegining,quantityListEnd+1, "quantityListBegining is not quantityListEnd+1");


    }

    @Test
    public void addFirstCardInNewList()  {

        //----Open 'QA 4 Auto' board
        driver.findElement(By.xpath("//div[@title='QA4 Auto']/..")).click();

        waitUntilElementIsClickable(By.cssSelector(".placeholder"),30);
        //--------Get qantity of 'Add another card' buttons at the beginning----
        int quantityAddAnotherButtonBeg = driver.findElements(By.xpath("//span[@class= 'js-add-another-card']")).size();

        //-----Add a new list------
        driver.findElement(By.cssSelector(".placeholder")).click();

        waitUntilElementIsVisible(By.cssSelector(".list-name-input"),10);
        driver.findElement(By.cssSelector(".list-name-input"))
                .sendKeys("New List");
        waitUntilElementIsClickable(By.xpath("//input[@type='submit']"),10);
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        waitUntilElementIsClickable(By.cssSelector("a.js-cancel-edit"),10);
        driver.findElement(By.cssSelector("a.js-cancel-edit")).click();

        waitUntilElementIsVisible(By.cssSelector(".placeholder"),10);
        //----- Get the last 'Add card' button----
        waitUntilAllElementsAreVisible(By.xpath("//span[@class = 'js-add-a-card']"),15);
        List<WebElement> listAddCardButtons = driver.findElements(By.xpath("//span[@class = 'js-add-a-card']"));
        int sizeLstAddCardButtons = listAddCardButtons.size();
        WebElement lastAddCardButton = listAddCardButtons.get(sizeLstAddCardButtons-1);
        //----Add a first card for any new list
        lastAddCardButton.click();

        waitUntilElementIsClickable(By
                .xpath("//input[@class='primary confirm mod-compact js-add-card']"),10);
        driver.findElement(By
                .xpath("//textarea[@placeholder='Enter a title for this card…']")).sendKeys("text");
        driver.findElement(By
                .xpath("//input[@class='primary confirm mod-compact js-add-card']")).click();

        waitUntilElementIsClickable(By.cssSelector("a.js-cancel"),10);
        driver.findElement(By.cssSelector("a.js-cancel")).click();

        //--------Get qantity of 'Add another card' buttons at the end----
        waitUntilAllElementsAreVisible(By.xpath("//span[@class= 'js-add-another-card']"),10);
        int quantityAddAnotherButtonEnd = driver.findElements(By.xpath("//span[@class= 'js-add-another-card']")).size();

        Assert.assertEquals(quantityAddAnotherButtonBeg+1, quantityAddAnotherButtonEnd);

    }



}
