package Helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import static com.codeborne.selenide.Selenide.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class Helpers {

    private static final By inputField = By.xpath("//span[@id='inbox-id']/input[@type='text']");

    public static void check(WebElement checkbox) {
//        if (!checkbox.isSelected()){
//            checkbox.click();
//        }
        setCheckboxTo(checkbox, true);
    }

    public static void uncheck(WebElement checkbox) {
//        if (checkbox.isSelected()){
//            checkbox.click();
//        }
        setCheckboxTo(checkbox, true);
    }


    private static void setCheckboxTo(WebElement checkbox, boolean value) {
        if (checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public static List<String> readAllLines(String resourcePath) throws IOException {
        return Files.readAllLines(new File(resourcePath).toPath(), Charset.defaultCharset());
    }




    }






