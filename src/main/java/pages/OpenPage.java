package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenPage {
    private String selectorExchangeRates = "//*[@class='main-page-exchange main-page-info__card']";
    private String selectorTableHeaders=".//tbody/tr[contains(@class,'header')]/td";
    private String selectorTableRows = ".//tbody/tr[contains(@class,'row')]";
    private WebDriver driver;

    private WebElement exchangeRates;
    private List<Map<String, String>> collectExchangeRates = new ArrayList<>();

    public WebDriver getDriver() {
        return driver;
    }

    public OpenPage(WebDriver driver) {
        this.driver = driver;
        exchangeRates = driver.findElement(By.xpath(selectorExchangeRates));
    }

    public WebElement getExchangeRates() {
        return exchangeRates;
    }

    public List<Map<String, String>> getCollectExchangeRates() {
        List<WebElement> tableHeaders = exchangeRates.findElements(By.xpath(selectorTableHeaders));
        List<WebElement> tableRows = exchangeRates.findElements(By.xpath(selectorTableRows));
        for (int i = 0; i < tableRows.size(); ++i) {
            Map<String, String> collectRow = new HashMap<>();
            for (int j = 0; j < tableHeaders.size(); ++j) {
                collectRow.put(
                    tableHeaders.get(j).getText(),
                    tableRows.get(i).findElement(By.xpath("./td["+(j+1)+"]")).getText()

                );
            }
            collectExchangeRates.add(collectRow);
        }
        return collectExchangeRates;
    }
}
