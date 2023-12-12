package PageObject.Elements;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.SelenideElement;

public class ProductCardElements {
    /**
     * Описание карточки товара
     */
    private static final SelenideElement photo = $(".inventory_details_img_container").as("Изображение товара");
    private static final SelenideElement productName = $("//div[@class='inventory_details_name large_size']").as("Название товара");
    private static final SelenideElement price = $(".inventory_details_price").as("Стоимость товара");
    private static final SelenideElement deleteButton = $("Кнопка удаления товара");
    private static final SelenideElement backToProductsButton = $("#back-to-products").as("Выйти из карточки товара на главную страницу");
    private static final SelenideElement addButton = $("//button[@class='btn btn_primary btn_small btn_inventory']").as("Кнопка добавления товара");


}
