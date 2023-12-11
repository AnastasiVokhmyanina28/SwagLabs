package PageObject.Elements;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class CardElements {
    /**
     * описание открытой карточки
     */
    private static final SelenideElement cards = $(".cart_item").as("Карточка товара в корзине");
    private static final SelenideElement productName = $(".inventory_item_name").as("Название товара");
    private static final SelenideElement price = $(".inventory_item_price").as("Стоимость товара");
    private static final SelenideElement continueShoppingButton = $("#continue-shopping").as("Кнопка 'Вернуться к покупкам'");
    private static final SelenideElement checkout = $("#checkout").as("Кнопка оформить заказ");
    private static final SelenideElement removeButton = $x("//button[@class='btn btn_secondary btn_small cart_button']")
            .as("Удалить карточку из корзины");


}
