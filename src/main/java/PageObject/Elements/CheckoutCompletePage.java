package PageObject.Elements;

import PageObject.Elements.MainPage.HomePage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class CheckoutCompletePage {
    /**
     * Страница подтверждения заказа
     */
    private final SelenideElement text = $(".complete-header").as("Текст, что заказ сделан");
    private final SelenideElement backHomeButton = $("#back-to-products").as("Кнопка на главную страницу");


    @Step("Вернуться на главный экран")
    public HomePage doClickButtonBackHome(){
        backHomeButton.click();
        return new HomePage();
    }
}
