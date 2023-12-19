package Servise.ChromeDriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;

import javax.annotation.Nonnull;

public class CustomChromeDriver implements WebDriverProvider {

    public CustomChromeDriver() {

    }

    @Nonnull
    @Override
    @SneakyThrows
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-cache");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-cookies");
        options.addArguments("-incognito");

        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;

        SeleniumManager.getInstance().getDriverPath(options, false);
        return new ChromeDriver(options);
    }
}
