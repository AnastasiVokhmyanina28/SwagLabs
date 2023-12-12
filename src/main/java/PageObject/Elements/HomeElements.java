package PageObject.Elements;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class HomeElements {
    private static final ElementsCollection cards = $$(".inventory_item").as("Карточки товаров на главное странице");
    private static final SelenideElement productSort = $(".active_option").as("Сортировка товара");
}
