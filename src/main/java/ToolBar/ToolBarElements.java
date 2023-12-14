package ToolBar;

import PageObject.Elements.CardsGoodsInTheCartElements;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public interface ToolBarElements {
    SelenideElement menuButton = $("#react-burger-menu-btn").as("Меню");
    SelenideElement container = $("#shopping_cart_container").as("Корзина покупок");
    SelenideElement badge = $(".shopping_cart_badge").as("Показатель количества товаров в корзине");


    @Step("Открыть корзину")
    default CardsGoodsInTheCartElements openContainer() {
        container.click();
        return new CardsGoodsInTheCartElements();
    }
}
