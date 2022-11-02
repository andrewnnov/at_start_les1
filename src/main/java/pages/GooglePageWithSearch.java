package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GooglePageWithSearch {

    private String selectorSearchItems = "//div[@class='g' and not (@data-hveid)]";
    private String selectorUrl = ".//a[@href]";
    private String selectorNamePage = ".//h3";
    //private String selectorDescription = ""


    private WebDriver driver;

    private List<WebElement> searchItems = new ArrayList<>();
    private List<Map<String, Object>> collectResults = new ArrayList<>();

    public GooglePageWithSearch(WebDriver driver) {
        this.driver = driver;
        this.searchItems = driver.findElements(By.xpath(selectorSearchItems));
    }

    public GooglePageWithSearch(WebDriver driver, String search) {
        this.driver = driver;
        this.driver.get("https://www.google.com/search?q=" + search);
        this.searchItems = driver.findElements(By.xpath(selectorSearchItems));
    }



    public List<Map<String, Object>> getCollectResults() {
        for (WebElement result : searchItems) {
            collectResults.add(Map.of(
                    "WEB_ELEMENT", result,
                    "URL", result.findElement(By.xpath(selectorUrl)).getText(),
                    "NAME_PAGE", result.findElement(By.xpath(selectorNamePage)).getText()
            ));
        }
        return collectResults;
    }

    public boolean goPage(String namePage) {
        WebElement pageLink = (WebElement) collectResults.stream()
                .filter(x->x.get("NAME_PAGE").toString().contains(namePage))
                .findFirst()
                .get().get("WEB_ELEMENT");

        pageLink.findElement(By.xpath(selectorUrl)).click();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String tab : tabs) {
            driver.switchTo().window(tab);
            if (driver.getTitle().contains(namePage)) {
                return true;
            }
        }
        Assertions.fail("Не удалось открыть вкладку, содержащую " + namePage);
        return false;
    }
}
