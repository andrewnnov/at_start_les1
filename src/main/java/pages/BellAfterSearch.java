package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BellAfterSearch extends BellBeforeSearch {

    List<WebElement> resultSearch;

    WebDriverWait wait = new WebDriverWait(chromeDriver, 120);

    public BellAfterSearch(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public List<WebElement> getResultSearch() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='short__desc']")));
        return chromeDriver.findElements(By.xpath("//*[@class='short__desc']"));
    }
}
