package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class BellPageFactory {

    protected WebDriver chromeDriver;

    @FindBy(how = How.ID, id="input-search")
    protected WebElement searchField;

    @FindBy(how = How.ID, id="button-search")
    protected WebElement searchButton;

    @FindBy(how = How.XPATH, using="//*[@class='short__desc']")
    protected List<WebElement> resultSearch;

    public BellPageFactory(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public List<WebElement> getResultSearch() {
        return resultSearch;
    }

    public void find(String keysFind) {
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();

    }
}
