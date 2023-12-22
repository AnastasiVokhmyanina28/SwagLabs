package PageObject.Elements;

import Data.models.ProductPojo;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.MainPage.ProductBox;
import PageObject.Elements.blocks.ToolBar.ProductsActions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class CardsGoodsInTheCartPage implements ProductsActions {
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


    public List<ProductBox> initProducts() {
        List<ProductBox> productBoxList = new ArrayList<>();
        if (!cards.isEmpty()) {
            cards.forEach(element -> productBoxList.add(new ProductBox(element)));
        }
        return productBoxList;
    }

    @Override
    public List<ProductPojo> getAllProducts() {
        List<ProductPojo> list = new ArrayList<>();
        initProducts().forEach(
                element -> list.add(element.toPojo(true))
        );
        return list;
    }

    @Override
    public List<ProductPojo> getProductsInCart() {
        return getAllProducts();
    }


    @Step("Вернуться к покупкам")
    public HomePage doClickButtonContinueShopping() {
        continueShoppingButton.click();
        return new HomePage();
    }

    @Step("Перейти к оформлению товара")
    public OrderFormPage doClickButtonCheckout() {
        checkout.click();
        return new OrderFormPage();
    }
}
