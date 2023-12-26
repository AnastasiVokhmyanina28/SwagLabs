package PageObject.Elements;

import Data.models.ProductPojo;
import PageObject.Elements.blocks.ToolBar.CostOfGoods;
import PageObject.Elements.blocks.ToolBar.ProductsActions;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

@Getter
public class ProductCardPage implements ToolBarElements, ProductsActions, CostOfGoods {
    /**
     * Описание карточки товара
     */
    private final ElementsCollection cart = $$("#inventory_item_container").as("Карта товара");
    private final SelenideElement photo = $(".inventory_details_img_container").as("Изображение товара");
    private final SelenideElement productName = $x("//div[@class='inventory_details_name large_size']").as("Название товара");
    private final SelenideElement price = $(".inventory_details_price").as("Стоимость товара");
    private final SelenideElement deleteButton = $("#remove-sauce-labs-backpack").as("Кнопка удаления товара");
    private final SelenideElement backToProductsButton = $("#back-to-products").as("Выйти из карточки товара на главную страницу");
    private final SelenideElement addButton = $("#add-to-cart-sauce-labs-backpack").as("Кнопка добавления товара");

    @Step
    public String getName() {
        return productName.getText();
    }

    @Step
    public BigDecimal getPrice() {
        return getCostOfGoods(price.getText());
    }

    @Step
    public Boolean inCart() {
        return deleteButton.isDisplayed();
    }

    public ProductPojo toPojo() {
        return new ProductPojo(getName(), getPrice(), inCart());
    }

    @Override
    @Step
    public List<ProductPojo> getAllProducts() {
        List<ProductPojo> result = new ArrayList<>();
        result.add(toPojo());
        return result;
    }

    @Override
    public List<ProductPojo> getProductsInCart() {
        return getAllProducts();
    }

    @Step("Проверка добавления товара в корзину из карточки товара")
    public void checkOfAddingAnItemToTheCart() {
        assertThat(getDeleteButton().exists()).as("Кнопка 'Remove' не отображается.").isTrue();
        assertThat(badge.isDisplayed()).as("При добавлении товара, на корзине не отображается уведомляющий знак").isTrue();

    }

}
