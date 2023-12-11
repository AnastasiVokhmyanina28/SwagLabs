package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ToolBarElements {
    private static final SelenideElement menuButton = $("#react-burger-menu-btn").as("Меню");
    private static final SelenideElement container = $("#shopping_cart_container").as("Корзина покупок");
}
