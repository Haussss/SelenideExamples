package Helpers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.reporters.ExitCodeListener;
import ru.yandex.qatools.allure.annotations.Attachment;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ScreenShoter extends ExitCodeListener {
@Override
    public void onTestFailure(ITestResult result
){super.onTestFailure(result);
makeScreenshot();
}
    @Attachment
    public byte[] makeScreenshot() {

        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
