package PageObject.Elements.MainPage;

import Data.models.ProductPojo;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.NonNull;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductBox {

    SelenideElement container;
    private SelenideElement itemName;
    private SelenideElement itemPrice;
    private SelenideElement addButton;
    private SelenideElement deleteButton;

    public ProductBox(@NonNull SelenideElement container) {
        this.container = container;
//todo
        itemName = container.$(".inventory_item_name");
        itemPrice = container.$(".inventory_item_price");
        addButton = container.$x(".//button[@class='btn btn_primary btn_small btn_inventory ']");
        deleteButton = container.$x(".//button[@class='btn btn_secondary btn_small btn_inventory ']");
    }

    @Step
    public String getName() {
        return itemName.getText();
    }

    @Step
    public Double getPrice() {
        return
                Double.valueOf(itemPrice.getText().replace("$", ""));
    }

    public ProductPojo toPojo() {
        return new ProductPojo(getName(), getPrice(), inCart());
    }

    public ProductPojo toPojo(Boolean inCart) {
        return new ProductPojo(getName(), getPrice(), inCart);
    }

    @Step
    public Boolean inCart() {
        return deleteButton.isDisplayed();
    }

    @Step
    public void addToCart() {
        addButton.click();
        assertThat(deleteButton.isDisplayed()).as("Товар не добавлен").isTrue();
    }

    @Step
    public void removeFromCart() {
        deleteButton.click();
        assertThat(addButton.isDisplayed()).as("Товар не удален").isTrue();
    }

}
