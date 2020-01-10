package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends  PageBase{
    @FindBy (id = "user")
    WebElement userField;
    @FindBy (id = "login")
    WebElement loginButton;
    @FindBy (xpath = "//button[@id='login-submit']//span[contains(text(),'Log in')]")
    WebElement theSecondLoginButton;
    @FindBy (xpath = "//button[@id='login-submit']//span[contains(text(),'Continue')]")
    WebElement continueButton;


    public LoginPageHelper(WebDriver driver){
        super(driver);
    }
    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(loginButton,30);
    }

    public void enterAtlLogin(String login) {
        enterValueToTheField(userField,login);
    }


    public void clickLoginWithAtlassian() {
        waitUntilElementIsClickable(loginButton,10);
        loginButton.click();
    }

    public void clickContinueButton() {
        waitUntilElementIsClickable(continueButton,30);
        continueButton.click();
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
        theSecondLoginButton.click();
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
