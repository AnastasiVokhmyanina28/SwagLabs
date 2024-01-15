package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public interface MenuPage {
    SelenideElement menuWindow = $(By.className("bm-menu")).as("Окно 'Меню'");
    SelenideElement logout = $(By.id("logout_sidebar_link")).as("Выход");
    SelenideElement buttonClose = $(By.id("react-burger-cross-btn")).as("Закрыть меню");

    @Step("Выход с сайта")
    default AuthorizationPage logOut() {
        logout.click();
        return new AuthorizationPage();
    }

}
