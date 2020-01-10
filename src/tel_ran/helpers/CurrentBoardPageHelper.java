package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CurrentBoardPageHelper extends PageBase{
    String name;
    public CurrentBoardPageHelper(WebDriver driver, String name) {
        super(driver);
        this.name = name;
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),30);
    }

    public boolean titleVerification() {
        return driver.findElement(By.xpath("//span[@dir='auto']"))
                .getText().equals(name);
    }

    public void createNewList(String name) {
        WebElement addListButton = driver.findElement(By.cssSelector(".placeholder"));
        addListButton.click();
        waitUntilElementIsVisible(By.cssSelector(".list-name-input"),10);
        driver.findElement(By.cssSelector(".list-name-input"))
                .sendKeys(name);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        waitUntilElementIsClickable(By.cssSelector("a.js-cancel-edit"),10);
        driver.findElement(By.cssSelector("a.js-cancel-edit")).click();
    }

    public String getAddButtonName() {
        WebElement addListButton = driver.findElement(By.cssSelector(".placeholder"));
        return addListButton.getText();
    }

    public boolean existsList(String nameList) {
        boolean exitName = false;
        for(WebElement element: driver.findElements(By.xpath("//h2/../textarea"))){
            //System.out.println("Name - " + element.getText());
            if(element.getText().equals(nameList)) exitName = true;
        }
        return exitName;

    }

    public int getQuantityLists() {
        return driver.findElements(By.xpath("//h2")).size();
    }
}
