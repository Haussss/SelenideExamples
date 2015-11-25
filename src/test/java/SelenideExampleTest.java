import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;


import Core.TestBase;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class SelenideExampleTest extends TestBase{

    @Test
    public void simpleTest(){

        open("/");
        $(By.linkText("Form Authentication")).click();
        $("#username").val("tomsmith");
        $("#password").val("SuperSecretPassword!");
        $("button[type='submit']").click();
        $("#flash").should(appear,cssClass("success"),text("You logged into a secure area! "));
//        $("#flash")
//                .should(appear)
//                .shouldHave(cssClass("success"))
//                .shouldHave(text("You logged into a secure area! "));

    }
}
