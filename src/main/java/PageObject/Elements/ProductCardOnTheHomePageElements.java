package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class ProductCardOnTheHomePageElements {
    //элементы с карточки товара на главной странице
    private static final SelenideElement productName = $("#item_4_title_link").as("Название товара");
    private static final SelenideElement price = $("//div[@class='inventory_item_label']/a[@id='item_4_title_link']/../following-sibling::div//div[@class='inventory_item_price']").as("Цена товара");

}
