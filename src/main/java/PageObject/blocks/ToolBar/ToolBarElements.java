package PageObject.blocks.ToolBar;

import PageObject.CardsGoodsInTheCartPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public interface ToolBarElements {
    SelenideElement container = $(By.id("shopping_cart_container")).as("Корзина покупок");
    SelenideElement badge = $(By.className("shopping_cart_badge")).as("Показатель количества товаров в корзине");
    SelenideElement menuButton = $(By.id("react-burger-menu-btn")).as("Кнопка открытия 'Меню'");


    @Step("Открыть корзину")
    default CardsGoodsInTheCartPage openCart() {
        container.click();
        return new CardsGoodsInTheCartPage();
    }

    @Step("Открыть меню")
    default void openMenu() {
        menuButton.click();
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
