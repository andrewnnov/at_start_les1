package ru.bellintegrator;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import steps.Steps;

import java.util.List;
import java.util.Map;

public class Tests extends BaseTest {

    @Feature("Проверка тайтла")
    @Test
    public void firstTestTitle() {
        //chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search&description=true&search=");
        chromeDriver.get("https://bellintegrator.ru/");
        String title = chromeDriver.getTitle();
        System.out.println(title);
        Assertions.assertTrue(title.contains("Bell Integrator"), "Title doesn't contains " + title);
    }

    @Feature("Проверка результатов происка")
    @Test
    public void secondTest() throws InterruptedException {
        chromeDriver.get("https://bellintegrator.ru/");
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='careers_friend']//img")));
        WebElement exitImgBtn = chromeDriver.findElement(By.xpath("//div[@id='careers_friend']//img"));
        exitImgBtn.click();
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search&description=true&search=");
        WebElement searchField = chromeDriver.findElement(By.id("input-search"));
        WebElement searchButton = chromeDriver.findElement(By.id("button-search"));
        searchField.click();
        searchField.sendKeys("RPA");
        searchButton.click();

        List<WebElement> resultSearch = chromeDriver.findElements(By.xpath("//*[@class='short__desc']"));
        resultSearch.forEach(x -> System.out.println(x.getText()));
        Assertions.assertTrue(resultSearch.stream().anyMatch(x -> x.getText().contains("Кирилл Филенков")),
                "Статьи RPA содержащие Кирилл Филенков не найдены");
    }

    @DisplayName("Проверка результатов поиска с помощью PageFactory")
    @Feature("Проверка результатов происка")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"RPA, Кирилл Филенков", "Нагрузочное тестирование, Сергей Минаев"})
    public void testPageObject(String keyWord, String result) {
        chromeDriver.get("https://bellintegrator.ru/");
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='careers_friend']//img")));
        WebElement exitImgBtn = chromeDriver.findElement(By.xpath("//div[@id='careers_friend']//img"));
        exitImgBtn.click();

        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search&description=true&search=");
        BellBeforeSearch bellBeforeSearch = new BellBeforeSearch(chromeDriver);
        bellBeforeSearch.find(keyWord);
        BellAfterSearch bellAfterSearch = new BellAfterSearch(chromeDriver);
        Assertions.assertTrue(bellAfterSearch.getResultSearch().stream().anyMatch(x -> x.getText()
                .contains(result)), "Статьи " + keyWord + " содержащие " +  result + " не найдены");

    }

    @DisplayName("Проверка результатов поиска с помощью PageFactory")
    @Feature("Проверка результатов происка")
    @ParameterizedTest()
    @CsvSource({"RPA, Кирилл Филенков", "Нагрузочное тестирование, Сергей Минаев"})
    public void testPageFactory(String keyWord, String result) {
        chromeDriver.get("https://bellintegrator.ru/");
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='careers_friend']//img")));
        WebElement exitImgBtn = chromeDriver.findElement(By.xpath("//div[@id='careers_friend']//img"));
        exitImgBtn.click();

        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search&description=true&search=");
        BellPageFactory bellPageFactory = PageFactory.initElements(chromeDriver, BellPageFactory.class);
        bellPageFactory.find(keyWord);
        Assertions.assertTrue(bellPageFactory.getResultSearch().stream().anyMatch(x -> x.getText()
                .contains(result)), "Статьи " + keyWord + " содержащие " +  result + " не найдены");

    }

    @Test
    public void testOpen() {
        GooglePageWithSearch googlePageWithSearch = new GooglePageWithSearch(chromeDriver, "открытие");
        List<Map<String, Object>> resultSearch = googlePageWithSearch.getCollectResults();
//        resultSearch.forEach(x -> System.out.println(x.get("NAME_PAGE").toString()));
        googlePageWithSearch.goPage("Банк Открытие");

        OpenPage openPage = new OpenPage(chromeDriver);
        List<Map<String, String>> collectExchangeRates = openPage.getCollectExchangeRates();
        System.out.println(collectExchangeRates);
        Assertions.assertTrue(
                Double.parseDouble(
                        collectExchangeRates.stream()
                                .filter(x -> x.get("Валюта обмена").contains("USD"))
                                .findFirst()
                                .get().get("Банк покупает").replace(",", ".")
                )
                        < Double.parseDouble(
                        collectExchangeRates.stream()
                                .filter(x -> x.get("Валюта обмена").contains("USD"))
                                .findFirst()
                                .get().get("Банк продает").replace(",", ".")
                )
        );
    }

    @Feature("Проверка курса валют")
    @DisplayName("Проверка курса валют со степами")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @Tag("Kotik")
    @CsvSource({"USD"})
    public void testOpenWithStep(String value) {
        GooglePageWithSearch googlePageWithSearch = new GooglePageWithSearch(chromeDriver, "открытие");
        List<Map<String, Object>> resultSearch = googlePageWithSearch.getCollectResults();
        Steps.checkContainsName(resultSearch, "Банк Открытие", chromeDriver);
        Steps.goPageText(googlePageWithSearch, "Банк Открытие");
        OpenPage page = new OpenPage(chromeDriver);
        Steps.checkCourse(value, page);
    }
}
