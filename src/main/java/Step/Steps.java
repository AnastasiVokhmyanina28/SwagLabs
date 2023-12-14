package Step;

import PageObject.Elements.CardsGoodsInTheCartElements;
import PageObject.Page.CardPage;
import PageObject.Page.CheckoutOverviewPage;
import PageObject.Page.HomePage;
import PageObject.Page.OrderFormPage;
import ToolBar.ToolBarElements;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class Steps implements ToolBarElements {

    private CardPage cardPage = new CardPage();
    private CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
    private HomePage homePage = new HomePage();
    private OrderFormPage orderForm = new OrderFormPage();

    private CardsGoodsInTheCartElements cardsGoodsInTheCartElements = new CardsGoodsInTheCartElements();


    @Step("Проверка добавления товара в корзину из карточки товара")
    public void checkOfAddingAnItemToTheCart() {
        assertThat(cardPage.productCardElements.getDeleteButton().exists()).as("Кнопка 'Remove' не отображается.").isTrue();
        assertThat(badge.isDisplayed()).as("При добавлении товара, на корзине не отображается уведомляющий знак").isTrue();
        assertThat(badge.getText()).isEqualTo("1");
    }

    @Step("Проверка соответствия товара в корзне и с главной страницы")
    public void cartOpeningCheck(String price) {
        assertThat(openContainer().getCards().isEmpty()).as("Корзина товаров пуста").isFalse();
        assertThat(cardsGoodsInTheCartElements.getProductName().getText()).as("Название товара отличается").isEqualTo(homePage.homeElements.getProductName().getText());
        assertThat(cardsGoodsInTheCartElements.getPrice().getText()).as("Стоимость товара отличается").isEqualTo(price);
        assertThat(cardsGoodsInTheCartElements.getRemoveButton().exists()).as("Кнопка 'Remove' не отображается").isTrue();
    }

    @Step("Проверка перехода на страницу заполнения данных заказчика")
    public void checkOpeningOfTheDataFillingForm() {
        assertThat(orderForm.orderFormElements.getTitle().exists()).as("Форма заполнения данных не обнаружена").isTrue();
    }

    @Step("Проверка соответствия товаров в корзине и при оформлении заказа")
    public void orderPlacement() {
        assertThat(cardsGoodsInTheCartElements.getProductName().getText()).as("Наименование товаров не совпадают").isEqualTo(overviewPage.overviewElements.getNameProduct().getText());
        assertThat(cardsGoodsInTheCartElements.getPrice().getText()).as("Стоимость товаров не совпадают").isEqualTo(overviewPage.overviewElements.getPriceOfGoods().getText());
        assertThat(cardsGoodsInTheCartElements.getPrice().getText()).as("Стоимость товаров не совпадают").isEqualTo(overviewPage.overviewElements.getItemTotal().getText().split(": ")[1]);
        assertThat(Double.parseDouble(overviewPage.overviewElements.getItemTotal().getText().split("\\$")[1])
                + Double.parseDouble(overviewPage.overviewElements.getTax().getText().split("\\$")[1])).as("")
                .isEqualTo(Double.parseDouble(overviewPage.overviewElements.getTotal().getText().split("\\$")[1]));
    }
}
