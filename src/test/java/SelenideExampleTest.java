import Core.TestBase;
import Helpers.Helpers;
import Helpers.ScreenShoter;
import Pages.FactoryHomePage;
import Pages.FactoryRegistrationPage;
import Pages.StaticHomePage;
import Pages.StaticRegistrationPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.testng.BrowserPerClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Issue;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;



@Listeners({BrowserPerClass.class, ScreenShoter.class})

public class SelenideExampleTest extends TestBase{
    private FactoryHomePage homePage;
    private FactoryRegistrationPage registrationPage;
    private static final String LINKS_FILE = "./src/main/resources/Links.txt";
    @BeforeTest
    public void setup(){
        registrationPage = open("login",FactoryRegistrationPage.class);

    }
    @Issue("issue id = 1")
    @TestCaseId("test-1")
    @Test
    public void simpleLoginTest(){

        open("/");
        $(By.linkText("Form Authentication")).click();
        $("#username").val("tomsmith");
        $("#password").val("SuperSecretPassword!");
        $("button[type='submit']").click();
        $("#flash").should(appear,cssClass("success"),text("You logged into a secure area! "));

    }
    @Issue("issue id = 2")
    @TestCaseId("test-2")
    @Test
    public void staticPageLoginTest(){
        StaticRegistrationPage.login("tomsmith", "SuperSecretPassword!");
        $(StaticRegistrationPage.FLASH).should(appear, cssClass("success"), text("You logged into a secure area!"));
    }
    @Issue("issue id = 7")
    @TestCaseId("test-7")
    @Test
    public void staticPageLogoutTest(){
        StaticRegistrationPage.login("tomsmith", "SuperSecretPassword!");
        StaticHomePage.logout();
        $(StaticRegistrationPage.FLASH).should(appear, cssClass("success"), text("You logged into a secure area!"));

    }
    @Issue("issue id = 3")
    @TestCaseId("test-3")
    @Test
    public void factoryPageLogoutTest(){
        homePage = registrationPage.login("tomsmith", "SuperSecretPassword!");
        registrationPage = homePage.logout();
        registrationPage.flash.should(appear, cssClass("success"), text("You logged out of the secure area!"));
    }
    @Issue("issue id = 4")
    @TestCaseId("test-4")
    @Test
    public void factoryPageLoginTest(){
        homePage = registrationPage.login("tomsmith", "SuperSecretPassword!");
        homePage.flash.should(appear, cssClass("success"), text("You logged into a secure area!"));
    }
    @Issue("issue id = 5")
    @TestCaseId("test-5")
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
    @Issue("issue id = 6")
    @TestCaseId("test-6")
    @Test
    public void linksTest() throws IOException {
        open("");
        List<String> links = Helpers.readAllLines(LINKS_FILE);
        String linkss[] = links.toArray(new String[links.size()]);
        $$(By.cssSelector("li a")).shouldHave(CollectionCondition.exactTexts(linkss));
    }


}
