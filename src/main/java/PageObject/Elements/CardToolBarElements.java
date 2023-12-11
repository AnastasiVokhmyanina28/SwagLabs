package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CardToolBarElements {

    private static final SelenideElement productSort = $(".active_option").as("Сортировка товара");
    private static final SelenideElement backToProductsButton = $(".back-to-products").as("Выйти из карточки товара на главную страницу");

}
