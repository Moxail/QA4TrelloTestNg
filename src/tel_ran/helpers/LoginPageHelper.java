package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageHelper extends  PageBase{
    //WebDriver driver;
    public LoginPageHelper(WebDriver driver){
        super(driver);
    }
    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(By.id("login"),30);
    }

    public void enterAtlLogin(String login) {
        WebElement userField = driver.findElement(By.id("user"));
        userField.click();
        userField.clear();
        userField.sendKeys(login);
    }

    public void clickLoginWithAtlassian() {
        waitUntilElementIsClickable(By.id("login"),10);
        driver.findElement(By.id("login")).click();
    }

    public void clickContinueButton() {
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("login-submit")).click();
    }

    public void loginToTrelloAsAtlassian(String login, String password){
        this.enterAtlLogin(login);
        this.clickLoginWithAtlassian();
        this.clickContinueButton();
        this.enterAtlPasswordAndLogin(password);
    }

    public void enterAtlPasswordAndLogin(String password) {
        waitUntilElementIsClickable(By.id("password"),30);
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-submit")).click();
    }

    public void waitPasswordError() {
        waitUntilElementIsVisible(By
                .xpath("//div[@id = 'login-error']/span"),10);
    }

    public boolean verifyIfPasswordErrorIsCorrect(){
        return driver.findElement(By
                .xpath("//div[@id = 'login-error']/span")).getText()
                .contains("Incorrect email address and / or password.");
    }
}
