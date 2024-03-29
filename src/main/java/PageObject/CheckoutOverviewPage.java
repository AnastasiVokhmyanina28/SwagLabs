package PageObject;

import Data.models.ProductPojo;
import PageObject.MainPage.HomePage;
import PageObject.MainPage.ProductBox;
import PageObject.blocks.ToolBar.CostOfGoods;
import PageObject.blocks.ToolBar.ProductsActions;
import PageObject.blocks.ToolBar.ToolBarElements;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Оформление заказа. Информация
 */

public class CheckoutOverviewPage implements ProductsActions, ToolBarElements, CostOfGoods {
    private final SelenideElement nameProduct = $(By.className("inventory_item_name")).as("Наименование товара");
    private final SelenideElement priceOfGoods = $(By.className("inventory_item_price")).as("Цена товара(когда в корзине товар 1)");
    private final SelenideElement itemTotal = $(By.className("summary_subtotal_label")).as("Общая сумма товаров");
    private final SelenideElement tax = $(By.className("summary_tax_label")).as("Налог");
    private final SelenideElement total = $x("//div[@class='summary_info_label summary_total_label']").as("Окончательная цена");
    private final SelenideElement cancelButton = $(By.id("cancel"));
    private final ElementsCollection cardList = $$(By.className("cart_item")).as("Список товаров");
    private final SelenideElement finishButton = $(By.id("finish"));

    public List<ProductBox> initProducts() {
        List<ProductBox> list = new ArrayList<>();
        if (!cardList.isEmpty()) {
            cardList.forEach(element -> list.add(new ProductBox(element)));
        }
        return list;
    }

    @Override
    public List<ProductPojo> getAllProducts() {
        List<ProductPojo> list = new ArrayList<>();
        initProducts().forEach(
                element -> list.add(element.toPojo(true))
        );
        return list;
    }


    @Step("Вернуться на главную страницу")
    public HomePage doClickButtonCancel() {
        cancelButton.click();
        return new HomePage();
    }

    @Step("Завершение оформления заказа")
    public CheckoutCompletePage doClickButtonFinish() {
        finishButton.click();
        return new CheckoutCompletePage();
    }

    @Override
    public List<ProductPojo> getProductsInCart() {
        return getAllProducts();
    }

    @Step("Проверка товаров в чеке. Проверка суммы")
    public CheckoutOverviewPage orderPlacement() {
        productsQuantityControl(getAllProducts().size());
        assertThat(
                (getCostOfGoods(itemTotal.getText())).add(getCostOfGoods(tax.getText())))
                .isEqualTo(getCostOfGoods(total.getText()));
        return this;
    }
}
