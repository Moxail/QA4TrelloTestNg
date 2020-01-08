package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageHelper extends PageBase{
    public HomePageHelper (WebDriver driver){
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By
                .xpath("//a[@class='btn btn-sm btn-link text-white']"), 40);
    }

    public void openLoginPage() {
        WebElement loginIcon = driver.findElement(By
                .xpath("//a[@class='btn btn-sm btn-link text-white']"));

        loginIcon.click();
    }
}
