package PageObject.Elements.blocks.ToolBar;

import PageObject.Elements.CardsGoodsInTheCartPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public interface ToolBarElements {
    SelenideElement menuButton = $("#react-burger-menu-btn").as("Меню");
    SelenideElement container = $("#shopping_cart_container").as("Корзина покупок");
    SelenideElement badge = $(".shopping_cart_badge").as("Показатель количества товаров в корзине");
    SelenideElement menu = $("#react-burger-menu-btn").as("Кнопка открытия 'Меню'");


    @Step("Открыть корзину")
    default CardsGoodsInTheCartPage openContainer() {
        container.click();
        return new CardsGoodsInTheCartPage();
    }

    @Step("Количество товаров на бейдже")
    default Integer getTheNumberOfItemsInTheCart() {
        return Integer.parseInt(badge.getText());
    }

    @Step("Проверка соответствия кол-ва товара в корзине и на бейдже")
    default void productsQuantityControl(Integer productsCount) {
        assertThat(productsCount).isEqualTo(getTheNumberOfItemsInTheCart());
    }
}
