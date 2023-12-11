package Servise.ChromeDriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;

public class CustomChromeDriver implements WebDriverProvider {

    public CustomChromeDriver() {

    }

    @Nonnull
    @Override
    @SneakyThrows
    public WebDriver createDriver(@Nonnull DesiredCapabilities desiredCapabilities) {

        System.setProperty("webdriver.chrome.driver", Thread.currentThread().getContextClassLoader().getResource("chromedriver.exe").toURI().getPath());
        System.setProperty("selenide.browser", "Chrome");

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

        return new ChromeDriver(options);
    }
}
