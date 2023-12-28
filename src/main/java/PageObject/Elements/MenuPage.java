package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;


public interface MenuPage {
    SelenideElement menuWindow = $(".bm-menu").as("Окно 'Меню'");
    SelenideElement logout = $("#logout_sidebar_link").as("Выход");
    SelenideElement buttonClose = $("#react-burger-cross-btn").as("Закрыть меню");

    @Step("Выход с сайта")
    default AuthorizationPage logOut() {
        logout.click();
        return new AuthorizationPage();
    }

}
