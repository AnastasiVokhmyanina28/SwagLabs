package PageObject.Elements;

import PageObject.Elements.MainPage.HomePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

/**
 * Оформление заказа. Информация
 */
@Getter
public class CheckoutOverviewPage {
    private final SelenideElement nameProduct = $(".inventory_item_name").as("Наименование товара");
    private final SelenideElement priceOfGoods = $(".inventory_item_price").as("Цена товара(когда в корзине товар 1)");
    private final SelenideElement itemTotal = $(".summary_subtotal_label").as("Общая сумма товаров");
    private final SelenideElement tax = $(".summary_tax_label").as("Налог");
    private final SelenideElement total = $x("//div[@class='summary_info_label summary_total_label']").as("Окончательная цена");
    private final SelenideElement cancelButton = $("#cancel");
    private final ElementsCollection cardList = $$(".cart_item");
    private final SelenideElement finishButton = $("#finish");

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

}
