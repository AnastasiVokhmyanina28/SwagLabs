package PageObject.Elements;

import Servise.Config.TestConfig;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.val;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.DataProvider;

import static com.codeborne.selenide.Selenide.*;

public class AuthorizationElements {
    private static final SelenideElement userName = $("#user-name").as("Поле ввода 'UserName'");
    private static final SelenideElement pass = $("#password").as("Поле ввода 'Password'");
    private static final SelenideElement logginButton = $("#login-button").as("Кнопка 'Login'");
    private static final SelenideElement title = $(".login_logo").as("Заголовок");

    static TestConfig config = ConfigFactory.create(TestConfig.class);
    static String standard_user = config.standardUser();
    static String password = config.password();
    static String visual_user = config.visualUser();

    @DataProvider(name = "authParamUser")

    public static Object[][] authParamUser() {
        return new Object[][]{
                {standard_user, password},
                {visual_user, password}
        };
    }

    @Step("Авторизация")
    public static void fillInFields(String name, String passwords) {
        userName.val(name);
        pass.val(passwords);
        logginButton.click();
    }
}
