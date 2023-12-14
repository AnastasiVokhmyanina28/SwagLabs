package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CheckoutOverviewElements {
    private final SelenideElement nameProduct = $(".inventory_item_name").as("Наименование товара");
    private final SelenideElement priceOfGoods = $(".inventory_item_price").as("Цена товара(когда в корзине товар 1)");
    private final SelenideElement itemTotal = $(".summary_subtotal_label").as("Общая сумма товаров");
    private final SelenideElement tax = $(".summary_tax_label").as("Налог");
    private final SelenideElement total = $x("//div[@class='summary_info_label summary_total_label']").as("Окончательная цена");
    private final SelenideElement cancelButton = $("#cancel");
    private final SelenideElement finishButton = $("#finish");


}
