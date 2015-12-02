package Core;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.isHtmlUnit;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.util.Properties;

public class TestBase {
    private static final String BASE_URL = "https://the-internet.herokuapp.com/";
    private Properties environment;
    @BeforeTest
    public void configure() {
        Configuration.timeout = 10000;
        Configuration.baseUrl = System.getProperty("baseUrl", BASE_URL);
      //  Configuration.browser = WebDriverRunner.CHROME;
        getEnvironmentProperties();
    }
@AfterTest
public void cleanUp() throws IOException {
    saveEnviroment();
}


        private void getEnvironmentProperties(){
            environment=new Properties();
            if(!isHtmlUnit()){
            Capabilities caps = ((RemoteWebDriver) getWebDriver()).getCapabilities();
            environment.put("properties.browser", caps.getBrowserName());
            environment.put("properties.browser.version", caps.getVersion());
            environment.put("properties.platform", caps.getPlatform().name());
            environment.put("properties.platform.version", caps.getPlatform().getMajorVersion()
                    + "." + caps.getPlatform().getMinorVersion());
            environment.put("properties.url", Configuration.baseUrl);
            }
            else environment.put("properties.browser", "htmlUnit");
        }
private void saveEnviroment(){
    File resultsFolder = new File("./target/allure-results");
    if(!resultsFolder.exists())
    {
        resultsFolder.mkdirs();
    }
    try(OutputStream out = new FileOutputStream("./target/allure-results/environment.properties"))
    {
        environment.store(out,"Allure enviroment properties");
    }catch (IOException ex){
        System.out.println(ex.getMessage());
    }
}

}
