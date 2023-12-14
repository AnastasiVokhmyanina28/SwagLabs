package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

/**
 * форма оформления заказа(ввод данных)
 */
@Getter
public class OrderFormElements {
    private final SelenideElement firstName = $("#first-name").as("Поле ввода 'first name'");
    private final SelenideElement lastName = $("#last-name").as("Поле ввода 'last name'");
    private final SelenideElement postalCode = $("#postal-code").as("Поле ввода 'postal code'");
    private final SelenideElement cancelButton = $("#cancel").as("Кнопка 'Cancel'");
    private final SelenideElement continueButton = $("#continue").as("Кнопка 'Continue'");
    private final SelenideElement title = $x("//span[@class='title']").as("Оформление заказа. Заполнение данных");

}
