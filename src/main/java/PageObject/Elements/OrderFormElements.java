package PageObject.Elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class OrderFormElements {
    private static final SelenideElement firstName = $("#first-name").as("Поле ввода 'first name'");
    private static final SelenideElement lastName = $("#last-name").as("Поле ввода 'last name'");
    private static final SelenideElement postalCode = $("#postal-code").as("Поле ввода 'postal code'");
    private static final SelenideElement cancelButton = $("#cancel").as("Кнопка 'Cancel'");
    private static final SelenideElement continueButton = $("#continue").as("Кнопка 'Continue'");
}
