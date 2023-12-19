package PageObject.Elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class CardsGoodsInTheCartPage {
    /**
     * описание карточки в корзине
     */
    private final ElementsCollection cards = $$(".cart_item").as("Карточки товара в корзине");
    private final SelenideElement cardList = $(".cart_list").as("Список товаров в корзине");
    private final SelenideElement productName = $(".inventory_item_name").as("Название товара");
    private final SelenideElement price = $(".inventory_item_price").as("Стоимость товара");
    private final SelenideElement continueShoppingButton = $("#continue-shopping").as("Кнопка 'Вернуться к покупкам'");
    private final SelenideElement checkout = $("#checkout").as("Кнопка оформить заказ");
    private final SelenideElement removeButton = $x("//button[@class='btn btn_secondary btn_small cart_button']")
            .as("Удалить карточку из корзины");


}
