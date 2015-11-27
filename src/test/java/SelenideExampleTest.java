import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;


import Core.TestBase;
import Helpers.Helpers;
import Pages.FactoryHomePage;
import Pages.FactoryRegistrationPage;
import Pages.StaticHomePage;
import Pages.StaticRegistrationPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

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
    public void linksTest() throws IOException {
        open("");
        List<String> links = Helpers.readAllLines(LINKS_FILE);
//        ElementsCollection linksFromTheInternet = $$(By.tagName("li a"));
        Object[] linksText = links.toArray();
       // $$(By.cssSelector("li a")).shouldHave(CollectionCondition.texts(linksText));

    }
}
