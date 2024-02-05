package Servise.ChromeDriver;

import PageObject.AuthorizationPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseClass {

    private static String baseUrl;

    static {
        try {
            Properties property = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/connectionData.properties");
            property.load(fis);
            baseUrl = property.getProperty("baseUrl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void setUp() {
        Configuration.browser = CustomChromeDriver.class.getCanonicalName();
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.pageLoadTimeout = 100_000L;
    }

    @BeforeMethod
    public static void setUpAll() {
        open(baseUrl);
    }

    @AfterClass
    public static void close() {
        closeWebDriver();
    }

    public AuthorizationPage openLoginPage(){
        return new AuthorizationPage();
    }
}
