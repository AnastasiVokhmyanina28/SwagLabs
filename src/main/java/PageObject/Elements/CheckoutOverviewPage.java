package PageObject.Elements;

import Data.models.ProductPojo;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.MainPage.ProductBox;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static org.assertj.core.api.Assertions.assertThat;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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
    private final ElementsCollection cardList = $$(".cart_item").as("Список товаров");
    private final SelenideElement finishButton = $("#finish");

    public List<ProductBox> initProducts() {
        List<ProductBox> list = new ArrayList<>();
        if (!cardList.isEmpty()) {
            cardList.forEach(element -> list.add(new ProductBox(element)));
        }
        return list;
    }

    public List<ProductPojo> getProduct(){
        List<ProductPojo> list = new ArrayList<>();
        initProducts().forEach(
                element -> list.add(element.toPojo())
        );
        return list;
    }

    public void assertListEqualityCheck(){
        assertThat(new CardsGoodsInTheCartPage().getProducts())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(new CheckoutOverviewPage().getProduct().toArray(new ProductPojo[0]));
    }


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
