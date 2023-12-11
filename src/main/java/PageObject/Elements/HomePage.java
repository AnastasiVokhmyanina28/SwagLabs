package PageObject.Elements;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.SelenideElement;

public class HomePage {
    private static final SelenideElement menuButton = $("#react-burger-menu-btn").as("Меню");
    private static final SelenideElement container = $("#shopping_cart_container").as("Корзина покупок");
    private static final SelenideElement productSort = $("#").as("Сортировка товара");

}
