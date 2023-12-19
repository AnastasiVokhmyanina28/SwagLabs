package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MenuPage {
    private final SelenideElement menu = $(".bm-menu").as("Окно 'Меню'");
    private final SelenideElement logout = $("#logout_sidebar_link").as("Выход");
    private final SelenideElement buttonClose = $("#react-burger-cross-btn").as("Закрыть меню");


}
