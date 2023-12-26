package PageObject.Elements;

import Person.Person;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * форма оформления заказа(ввод данных)
 */
@Getter
public class OrderFormPage {
    private final SelenideElement firstName = $("#first-name").as("Поле ввода 'first name'");
    private final SelenideElement lastName = $("#last-name").as("Поле ввода 'last name'");
    private final SelenideElement postalCode = $("#postal-code").as("Поле ввода 'postal code'");
    private final SelenideElement cancelButton = $("#cancel").as("Кнопка 'Cancel'");
    private final SelenideElement continueButton = $("#continue").as("Кнопка 'Continue'");
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
    public void assertPageActive() {
        assertThat(getTitle().exists()).as("Форма заполнения данных не обнаружена").isTrue();
    }

    @Step("Заполнить форму данными")
    public void dataFillingPerson() {
        Person person = Person.randomized();

        getFirstName().setValue(person.getName());
        getLastName().setValue(person.getLastName());
        getPostalCode().setValue(person.getPostalCode());
    }
}
