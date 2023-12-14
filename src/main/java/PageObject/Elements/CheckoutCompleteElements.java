package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CheckoutCompleteElements {
    private final SelenideElement text = $(".complete-header").as("Текст, что заказ сделан");
    private final SelenideElement backHomeButton = $("#back-to-products").as("Кнопка на главную страницу");
}
