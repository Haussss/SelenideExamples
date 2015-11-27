package Pages;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static Helpers.Locators.get;

public class StaticRegistrationPage {
    public final static By USER_NAME_FIELD = get("Registr.user");
    public final static By PASSWORD_FIELD = get("Registr.pswd");
    public final static By LOGIN_BUTTON = get("Registr.button");
    public final static By FLASH = get("homePage.flash");

    public static void login(String user, String pswd){
        $(USER_NAME_FIELD).val(user);
        $(PASSWORD_FIELD).val(pswd);
        $(LOGIN_BUTTON).click();
    }
}
