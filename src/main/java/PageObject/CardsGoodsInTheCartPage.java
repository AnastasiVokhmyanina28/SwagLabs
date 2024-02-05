package PageObject;

import Data.models.ProductPojo;
import PageObject.MainPage.HomePage;
import PageObject.MainPage.ProductBox;
import PageObject.blocks.ToolBar.ProductsActions;
import PageObject.blocks.ToolBar.ToolBarElements;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CardsGoodsInTheCartPage implements ProductsActions, ToolBarElements {
    /**
     * описание карточки в корзине
     */
    private final ElementsCollection cards = $$(By.className("cart_item_label")).as("Карточки товара в корзине");
    private final SelenideElement cardList = $(By.className("cart_list")).as("Список товаров в корзине");
    private final ElementsCollection productName = $$(By.className("inventory_item_name")).as("Название товара");
    private final ElementsCollection price = $$(By.className("inventory_item_price")).as("Стоимость товара");
    private final SelenideElement continueShoppingButton = $(By.id("continue-shopping")).as("Кнопка 'Вернуться к покупкам'");
    private final SelenideElement checkout = $(By.id("checkout")).as("Кнопка оформить заказ");
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
    public OrderFormPage openOrderPage() {
        checkout.click();
        return new OrderFormPage();
    }

    @Step("Проверка, что при добавлении товара корзина не пустая")
    public CardsGoodsInTheCartPage cartOpeningCheck() {
        assertThat(cards.isEmpty()).as("Корзина товаров пуста").isFalse();
        productsQuantityControl(cards.size());
        return this;
    }

    @Step("Удаление товара из корзины")
    public CardsGoodsInTheCartPage deleteProduct() {
        initProducts().stream().forEach(ProductBox::removeFromCart);

        assertThat(badge.isDisplayed()).as("Бейдж количества товаров в корзине отображается").isFalse();
        assertThat(cards.isEmpty()).as("Товар из корзины не удален").isTrue();
        return this;
    }

}
