package tel_ran.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class CurrentBoardPageTests extends TestBase{
    @BeforeMethod
    public void initTest(){
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

    }

    @Test
    public void createNewList() throws InterruptedException {

        //----Open 'QA 4 Auto' board
        waitUntilElementIsVisible(By.xpath("//div[@title='QA4 Auto']/.."),20);
        driver.findElement(By.xpath("//div[@title='QA4 Auto']/..")).click();
        //Thread.sleep(15000);
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),30);
        //-----Add a new list------
        WebElement addListButton = driver.findElement(By.cssSelector(".placeholder"));
        String nameAddListButton = addListButton.getText();
        addListButton.click();
        //Thread.sleep(5000);
        waitUntilElementIsVisible(By.cssSelector(".list-name-input"),10);
        String str = genRandomString(7);
        //String str = "New List";
        System.out.println("Name button - " + nameAddListButton);
        int quantityListAtFirst = driver.findElements(By.xpath("//h2")).size();
        if(nameAddListButton.equals("Add another list")){
            boolean exitName = false;
            System.out.println("Size-" + driver.findElements(By.xpath("//h2/../textarea")).size());
            for(WebElement element: driver.findElements(By.xpath("//h2/../textarea"))){
                System.out.println("Name - " + element.getText());
                if(element.getText().equals(str)) exitName = true;
            }
            if(exitName) str = stringWithRandomNumber(1000,str);
        }

        driver.findElement(By.cssSelector(".list-name-input"))
                .sendKeys(str);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        //System.out.println("name of the list - " + genRandomString(5));
        Thread.sleep(5000);
        int quantityListAtTheEnd = driver
                .findElements(By.xpath("//h2")).size();
        Assert.assertEquals(quantityListAtFirst+1,quantityListAtTheEnd);

    }

    @Test
    public void addFirstCardInNewList() throws InterruptedException {

        //----Open 'QA 4 Auto' board
        driver.findElement(By.xpath("//div[@title='QA4 Auto']/..")).click();
        Thread.sleep(15000);
        //-----Add a new list------
        driver.findElement(By.cssSelector(".placeholder")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".list-name-input"))
                .sendKeys("New List");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);
        //----Add a first card for any new list
        driver.findElements(By.xpath("//span[@class='js-add-a-card']")).get(0).click();
        Thread.sleep(3000);
        driver.findElement(By
                .xpath("//textarea[@placeholder='Enter a title for this card…']")).sendKeys("text");
        driver.findElement(By
                .xpath("//input[@class='primary confirm mod-compact js-add-card']")).click();
        Thread.sleep(5000);


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
