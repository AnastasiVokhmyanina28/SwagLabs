package PageObject.Elements;

import Data.UserData;
import Data.Users;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthorizationElements {
    private final SelenideElement userName = $("#user-name").as("Поле ввода 'UserName'");
    private final SelenideElement pass = $("#password").as("Поле ввода 'Password'");
    private final SelenideElement logginButton = $("#login-button").as("Кнопка 'Login'");
    private final SelenideElement title = $(".app_logo").as("Заголовок с главной страницы");

    @DataProvider()
    public static UserData[] authParamUser() {
        return List.of(
                Users.STANDARD.getUserData(),
                Users.VISUAL.getUserData()
        ).toArray(new UserData[0]);
    }

    @Step("Авторизация")
    public void fillInFields(String name, String passwords) {
        userName.val(name);
        pass.val(passwords);
        logginButton.click();
        assertThat(title.isDisplayed()).as("Заголовок не отображается. Пользователь не прошел авторизацию").isTrue();
    }
}
