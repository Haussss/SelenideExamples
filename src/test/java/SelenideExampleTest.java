import Core.TestBase;
import Helpers.Helpers;
import Pages.FactoryHomePage;
import Pages.FactoryRegistrationPage;
import Pages.StaticRegistrationPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.testng.BrowserPerClass;
import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import com.thoughtworks.selenium.ScreenshotListener;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Attachment;
import Helpers.ScreenShooter;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Listeners({BrowserPerClass.class, Helpers.ScreenShooter.class})

public class SelenideExampleTest extends TestBase{
    private FactoryHomePage homePage;
    private FactoryRegistrationPage registrationPage;
    private static final String LINKS_FILE = "./src/main/resources/Links.txt";
    @BeforeTest
    public void setup(){
        registrationPage = open("login",FactoryRegistrationPage.class);

    }

    @Test
    public void simpleLoginTest(){

        open("/");
        $(By.linkText("Form Authentication")).click();
        $("#username").val("tomsmith");
        $("#password").val("SuperSecretPassword!");
        $("button[type='submit']").click();
        $("#flash").should(appear,cssClass("success"),text("You logged into a secure area! "));

    }
    @Test
    public void staticPageLoginTest(){
        StaticRegistrationPage.login("tomsmith", "SuperSecretPassword!");
        $(StaticRegistrationPage.FLASH).should(appear, cssClass("success"), text("You logged into a secure area!"));
    }
    @Test
    public void staticPageLogoutTest(){
        homePage = registrationPage.login("tomsmith", "SuperSecretPassword!");
        registrationPage = homePage.logout();
        registrationPage.flash.should(appear, cssClass("success"), text("You logged out of the secure area!"));
    }

    @Test
    public void factoryPageLoginTest(){
        homePage = registrationPage.login("tomsmith", "SuperSecretPassword!");
        homePage.flash.should(appear, cssClass("success"), text("You logged into a secure area!"));
    }

    @Test
    public void linkssTest() throws IOException {
        open("");
        List<String> links = Helpers.readAllLines(LINKS_FILE);
        ElementsCollection linkes = $$(By.cssSelector("li a"));
        String[] linkk = linkes.getTexts();
        List<String> ddd = Arrays.asList(linkk);
        links.removeAll(ddd);
        Assert.assertTrue(links.isEmpty(), "Elements are no equals: " + links.toString());
    }

    @Test
    public void linksTest() throws IOException {
        open("");
        List<String> links = Helpers.readAllLines(LINKS_FILE);
        String linkss[] = links.toArray(new String[links.size()]);
        $$(By.cssSelector("li a")).shouldHave(CollectionCondition.exactTexts(linkss));
    }


}
