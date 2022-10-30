package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BellBeforeSearch {

    protected WebDriver chromeDriver;
    protected WebElement searchField;
    protected WebElement searchButton;

    public BellBeforeSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.searchField = chromeDriver.findElement(By.id("input-search"));
        this.searchButton = chromeDriver.findElement(By.id("button-search"));
    }

    public void find(String keysFind) {
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();

    }


}
