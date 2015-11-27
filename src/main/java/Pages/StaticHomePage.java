package Pages;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static Helpers.Locators.get;

public class StaticHomePage {
    public final static By FLASH = get("homePage.flash");
    public final static By LOGOUT_BUTTON = get("homepage.logoutButton");
    public static void logout(){
        $(LOGOUT_BUTTON).click();
    }
}
