package PageObject.Elements;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ProductCardElements {
    /**
     * Описание карточки товара
     */
    private final SelenideElement photo = $(".inventory_details_img_container").as("Изображение товара");
    private final SelenideElement productName = $x("//div[@class='inventory_details_name large_size']").as("Название товара");
    private final SelenideElement price = $(".inventory_details_price").as("Стоимость товара");
    private final SelenideElement deleteButton = $("#remove-sauce-labs-backpack").as("Кнопка удаления товара");
    private final SelenideElement backToProductsButton = $("#back-to-products").as("Выйти из карточки товара на главную страницу");
    private final SelenideElement addButton = $("#add-to-cart-sauce-labs-backpack").as("Кнопка добавления товара");


}
