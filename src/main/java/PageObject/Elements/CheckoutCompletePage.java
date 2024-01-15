package PageObject.Elements;

import PageObject.Elements.MainPage.HomePage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;


public class CheckoutCompletePage {
    /**
     * Страница подтверждения заказа
     */
    private final SelenideElement text = $(By.className("complete-header")).as("Текст, что заказ сделан");
    private final SelenideElement backHomeButton = $(By.id("back-to-products")).as("Кнопка на главную страницу");


    @Step("Вернуться на главный экран")
    public HomePage doClickButtonBackHome() {
        backHomeButton.click();
        return new HomePage();
    }

    @Step("Проверка подтверждения отправки заказа")
    public CheckoutCompletePage orderConfirmation() {
        assertThat(text.exists()).as("Заказ не отправлен").isTrue();
        return this;
    }
}
