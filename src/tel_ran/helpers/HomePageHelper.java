package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageHelper extends PageBase{
    @FindBy(xpath = "//a[@class='btn btn-sm btn-link text-white']")
    WebElement loginIcon;

    public HomePageHelper (WebDriver driver){
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginIcon, 40);
    }

    public void openLoginPage() {
        loginIcon.click();
    }
}
