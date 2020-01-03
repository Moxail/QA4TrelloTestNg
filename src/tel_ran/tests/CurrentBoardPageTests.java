package tel_ran.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class CurrentBoardPageTests extends TestBase{
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

    }

    @Test
    public void createNewList()  {

        //----Open 'QA 4 Auto' board
        waitUntilElementIsVisible(By.xpath("//div[@title='QA4 Auto']/.."),20);
        driver.findElement(By.xpath("//div[@title='QA4 Auto']/..")).click();
        //Thread.sleep(15000);
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),30);
        //-----Add a new list------
        WebElement addListButton = driver.findElement(By.cssSelector(".placeholder"));
        String nameAddListButton = addListButton.getText();
        addListButton.click();
        waitUntilElementIsVisible(By.cssSelector(".list-name-input"),10);
        String str = genRandomString(7);
        //System.out.println("Name button - " + nameAddListButton);
        int quantityListAtFirst = driver.findElements(By.xpath("//h2")).size();
        if(nameAddListButton.equals("Add another list")){
            boolean exitName = false;
            //System.out.println("Size-" + driver.findElements(By.xpath("//h2/../textarea")).size());
            for(WebElement element: driver.findElements(By.xpath("//h2/../textarea"))){
                //System.out.println("Name - " + element.getText());
                if(element.getText().equals(str)) exitName = true;
            }
            if(exitName) str = stringWithRandomNumber(1000,str);
        }

        driver.findElement(By.cssSelector(".list-name-input"))
                .sendKeys(str);
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        waitUntilElementIsClickable(By.cssSelector("a.js-cancel-edit"),10);
        driver.findElement(By.cssSelector("a.js-cancel-edit")).click();
        waitUntilElementIsVisible(By.cssSelector("span.placeholder"),10);
        int quantityListAtTheEnd = driver
                .findElements(By.xpath("//h2")).size();
        Assert.assertEquals(quantityListAtFirst+1,quantityListAtTheEnd);
        Assert.assertEquals(driver.findElement(By.cssSelector("span.placeholder")).getText(),"Add another list");

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

    public static String genRandomString(int num){
        String str = "";
        int number;
        Random gen = new Random();
        for(int i=0; i<num; i++){
            number = '!' + gen.nextInt('z' - '!' +1);
            str = str + (char)number;
        }
        return str;
    }

    public static String stringWithRandomNumber(int num,String str){
        Random gen = new Random();
        return str + gen.nextInt(num);
    }

}
