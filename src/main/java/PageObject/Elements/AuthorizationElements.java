package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class AuthorizationElements {
    private static final SelenideElement userName = $("#user-name").as("Поле ввода 'UserName'");
    private static final SelenideElement password = $("#password").as("Поле ввода 'Password'");
    private static final SelenideElement logginButton = $("#login-button").as("Кнопка 'Login'");
    private static final SelenideElement title = $(".login_logo").as("Заголовок");

}
