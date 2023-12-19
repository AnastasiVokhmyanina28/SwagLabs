package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class CheckoutCompletePage {
    /**
     * Страница подтверждения заказа
     */
    private final SelenideElement text = $(".complete-header").as("Текст, что заказ сделан");
    private final SelenideElement backHomeButton = $("#back-to-products").as("Кнопка на главную страницу");
}
