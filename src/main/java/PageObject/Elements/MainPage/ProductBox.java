package PageObject.Elements.MainPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.NonNull;


public class ProductBox {

    SelenideElement container;

    public ProductBox(@NonNull SelenideElement container) {
        this.container = container;
    }

    SelenideElement itemName = container.$(".inventory_item_name");
    SelenideElement itemPrice = container.$(".inventory_item_price");

    SelenideElement addButton = container.$x("//button[@class='btn btn_primary btn_small btn_inventory ']");
    SelenideElement deleteButton = container.$x("//button[@class='btn btn_secondary btn_small btn_inventory ']");


    @Step
    public String getName() {
        return itemName.getText();
    }

    @Step
    public Double getPrice() {
        return
                Double.valueOf(itemPrice.getText().replace("$", ""));
    }

    @Step
    public Boolean inCart() {
        if (deleteButton.isDisplayed()) {
            return true;
        } else return false;
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
