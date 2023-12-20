package PageObject.Elements;

import Data.models.ProductPojo;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class CardsGoodsInTheCartPage {
    /**
     * описание карточки в корзине
     */
    private final ElementsCollection cards = $$(".cart_item_label").as("Карточки товара в корзине");
    private final SelenideElement cardList = $(".cart_list").as("Список товаров в корзине");
    private final ElementsCollection productName = $$(".inventory_item_name").as("Название товара");
    private final ElementsCollection price = $$(".inventory_item_price").as("Стоимость товара");
    private final SelenideElement continueShoppingButton = $("#continue-shopping").as("Кнопка 'Вернуться к покупкам'");
    private final SelenideElement checkout = $("#checkout").as("Кнопка оформить заказ");
    private final SelenideElement removeButton = $x("//button[@class='btn btn_secondary btn_small cart_button']")
            .as("Удалить карточку из корзины");


    @Step
    public List<ProductPojo> productList() {
        List<ProductPojo> list = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            list.add(new ProductPojo(productName.get(i).getText(), Double.parseDouble(price.get(i).getText().split("\\$")[1])));
        }
        return list;
    }
}
