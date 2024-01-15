package PageObject.Elements;

import Data.User.UserData;
import Data.User.Users;
import PageObject.Elements.MainPage.HomePage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;


public class AuthorizationPage {
    private final SelenideElement userName = $(By.id("user-name")).as("Поле ввода 'UserName'");
    private final SelenideElement pass = $(By.id("password")).as("Поле ввода 'Password'");
    private final SelenideElement loginButton = $(By.id("login-button")).as("Кнопка 'Login'");
    private final SelenideElement title = $(By.className("app_logo")).as("Заголовок с главной страницы");

    @DataProvider()
    public static UserData[] authParamUser() {
        return List.of(
                Users.STANDARD.getUserData(),
                Users.VISUAL.getUserData()
        ).toArray(new UserData[0]);
    }

    public HomePage login(UserData data) {
        return login(data.getUser(), data.getPassword());
    }

    @Step("Авторизация")
    public HomePage login(String name, String passwords) {
        userName.setValue(name);
        pass.setValue(passwords);
        loginButton.click();
        assertThat(title.isDisplayed()).as("Заголовок не отображается. Пользователь не прошел авторизацию").isTrue();
        return new HomePage();
    }

    @Step("Отображение страницы авторизации")
    public AuthorizationPage checkTheAuthorizationPage() {
        assertThat(loginButton.isDisplayed()).isTrue();
        return this;
    }
}
