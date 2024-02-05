package PageObject;

import Person.Person;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * форма оформления заказа(ввод данных)
 */

public class OrderFormPage {
    private final SelenideElement firstName = $(By.id("first-name")).as("Поле ввода 'first name'");
    private final SelenideElement lastName = $(By.id("last-name")).as("Поле ввода 'last name'");
    private final SelenideElement postalCode = $(By.id("postal-code")).as("Поле ввода 'postal code'");
    private final SelenideElement cancelButton = $(By.id("cancel")).as("Кнопка 'Cancel'");
    private final SelenideElement continueButton = $(By.id("continue")).as("Кнопка 'Continue'");
    private final SelenideElement title = $x("//span[@class='title']").as("Оформление заказа. Заполнение данных");

    @Step("Вернуться в корзину")
    public CardsGoodsInTheCartPage doClickButtonCancel() {
        cancelButton.click();
        return new CardsGoodsInTheCartPage();
    }

    @Step("Перейти к информации заказа")
    public CheckoutOverviewPage doClickButtonContinue() {
        continueButton.click();
        return new CheckoutOverviewPage();
    }

    @Step("Проверка, что страница открыта")
    public OrderFormPage assertPageActive() {
        assertThat(title.exists()).as("Форма заполнения данных не обнаружена").isTrue();
        return this;
    }

    @Step("Заполнить форму данными")
    public OrderFormPage dataFillingPerson() {
        Person person = Person.randomized();

        firstName.setValue(person.getName());
        lastName.setValue(person.getLastName());
        postalCode.setValue(person.getPostalCode());
        return new OrderFormPage();
    }
}
