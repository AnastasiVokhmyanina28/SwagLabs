package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ToolBarElements {
    private final SelenideElement menuButton = $("#react-burger-menu-btn").as("Меню");
    private final SelenideElement container = $("#shopping_cart_container").as("Корзина покупок");

    @Step("Открыть корзину")
    public CardsGoodsInTheCartElements openContainer() {
        container.click();
        return new CardsGoodsInTheCartElements();
    }
}
