package PageObject.Elements;

import static com.codeborne.selenide.Selenide.*;

import ToolBar.ToolBarElements;
import com.codeborne.selenide.ElementsCollection;

import static org.assertj.core.api.Assertions.assertThat;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

@Getter
public class HomeElements implements ToolBarElements {
    private final ElementsCollection cards = $$(".inventory_item").as("Карточки товаров на главное странице");
    private final SelenideElement productSort = $(".active_option").as("Сортировка товара");
    private final SelenideElement productName = $("#item_4_title_link").as("Название товара");
    private final SelenideElement deleteButton = $("#remove-sauce-labs-backpack").as("Кнопка удаления товара");
    public final SelenideElement allDeleteButton = $x("//button[contains(@id, 'remove')]").as("Все кнопки удаления");
    public final ElementsCollection allButtons = $$x("//div[@class='inventory_item']//button").as("Кнопки из карточки товара");
    private final SelenideElement addButton = $("#add-to-cart-sauce-labs-backpack").as("Кнопка добавления товара");
    private final SelenideElement price = $x("//div[@class='inventory_item_label']/a[@id='item_4_title_link']/../following-sibling::div//div[@class='inventory_item_price']").as("Цена товара");

    @Step("Добавление товара в корзину")
    public void addItemToCart() {
        addButton.click();
        assertThat(deleteButton.isDisplayed()).as("Кнопка 'Remove' не отображается. Товар не добавлен в корзину").isTrue();
        assertThat(badge.isDisplayed()).as("При добавлении товара, на корзине не отображается уведомляющий знак").isTrue();
        assertThat(container.getText()).isEqualTo("1");
    }

    @Step("Добавление нескольких товаров в корзину")
    public static void addMultipleItemsToCart() {

    }
}
