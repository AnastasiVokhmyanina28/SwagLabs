package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class MenuPage {
    private final SelenideElement menu = $(".bm-menu").as("Окно 'Меню'");
    private final SelenideElement logout = $("#logout_sidebar_link").as("Выход");
    private final SelenideElement buttonClose = $("#react-burger-cross-btn").as("Закрыть меню");

    @Step("Выход с сайта")
    public AuthorizationPage logOut() {
        logout.click();
        return new AuthorizationPage();
    }

}
