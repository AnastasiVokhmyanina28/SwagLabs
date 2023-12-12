package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MenuElements {
    private static final SelenideElement menu = $(".bm-menu").as("Окно 'Меню'");
    private static final SelenideElement logout = $("#logout_sidebar_link").as("Выход");
    private static final SelenideElement buttonClose = $("#react-burger-cross-btn").as("Закрыть меню");


}
