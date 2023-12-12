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
    private static final SelenideElement userName = $("#user-name").as("Поле ввода 'UserName'");
    private static final SelenideElement pass = $("#password").as("Поле ввода 'Password'");
    private static final SelenideElement logginButton = $("#login-button").as("Кнопка 'Login'");
    private static final SelenideElement title = $(".app_logo").as("Заголовок");

    @DataProvider()
    public static UserData[] authParamUser() {
        return List.of(
                Users.STANDARD.getUserData(),
                Users.VISUAL.getUserData()
        ).toArray(new UserData[0]);
    }

    @Step("Авторизация")
    public static void fillInFields(String name, String passwords) {
        userName.val(name);
        pass.val(passwords);
        logginButton.click();
        assertThat(title.isDisplayed()).as("Заголовок не отображается. Пользователь не прошел авторизацию").isTrue();
    }
}
