package ru.bellintegrator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver chromeDriver;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @AfterEach
    public void afterEach() {
        chromeDriver.quit();
    }
}
