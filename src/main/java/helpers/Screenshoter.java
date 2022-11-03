package helpers;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Screenshoter {

    @Attachment
    public static byte[] getScreen(WebDriver driver) {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/main/resources/screen.png"));
            return Files.readAllBytes(Paths.get("src/main/resources", "screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.fail("Не удалось сохранить сркиншот на диск");
        }

        return new byte[0];
    }

    @Attachment
    public static byte[] getScreen(WebDriver driver, WebElement webElement) {

        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/main/resources/screen.png"));
            return Files.readAllBytes(Paths.get("src/main/resources", "screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.fail("Не удалось сохранить сркиншот на диск");
        }

        return new byte[0];
    }
}
