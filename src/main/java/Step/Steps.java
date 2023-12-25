package Step;

import PageObject.Elements.*;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.CostOfGoods;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class Steps implements ToolBarElements, CostOfGoods {

    private ProductCardPage productCardPage = new ProductCardPage();
    private CardsGoodsInTheCartPage shoppingContainerPage = new CardsGoodsInTheCartPage();
    private CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
    private HomePage homePage = new HomePage();
    private OrderFormPage orderForm = new OrderFormPage();
    private AuthorizationPage authorizationPage = new AuthorizationPage();
    private CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();
    private Integer quantityOfGoods;

    private CardsGoodsInTheCartPage cardsGoodsInTheCartElements = new CardsGoodsInTheCartPage();

    //todo
    @Step("Проверка добавления товара в корзину из карточки товара")
    public void checkOfAddingAnItemToTheCart() {
        assertThat(productCardPage.getDeleteButton().exists()).as("Кнопка 'Remove' не отображается.").isTrue();

        assertThat(badge.isDisplayed()).as("При добавлении товара, на корзине не отображается уведомляющий знак").isTrue();

    }

    @Step("Проверка соответствия товара в корзне и с главной страницы")
    public void cartOpeningCheck(String price) {

        assertThat(openContainer().getCards().isEmpty()).as("Корзина товаров пуста").isFalse();
        /**Проверка, что на бейдже кол-во товаров такое же как и кол-во добавленных товаров в корзине */
        productsQuantityControl(shoppingContainerPage.getCards().size());




        assertThat(cardsGoodsInTheCartElements.getProductName().get(0).getText()).as("Название товара отличается")
                .isEqualTo(homePage.getProductName().getText());

        assertThat(cardsGoodsInTheCartElements.getPrice().get(0).getText()).as("Стоимость товара отличается").isEqualTo(price);
        assertThat(cardsGoodsInTheCartElements.getRemoveButton().exists()).as("Кнопка 'Remove' не отображается").isTrue();
    }

    @Step("Проверка добавления товаров в корзину с главной страницы (число на бейдже соответствует кол-ву элементов в корзине)")
    public void checkingTheAdditionOfGoods() {
        productsQuantityControl(homePage.getProductsInCart().size());
    }


    @Step("Проверка перехода на страницу заполнения данных заказчика")
    public void checkOpeningOfTheDataFillingForm() {
        assertThat(orderForm.getTitle().exists()).as("Форма заполнения данных не обнаружена").isTrue();
    }

    @Step("Проверка соответствия товаров в корзине и при оформлении заказа")
    public void orderPlacement() {
        productsQuantityControl(overviewPage.getAllProducts().size());
        assertThat(
                (getCostOfGoods(overviewPage.getItemTotal().getText())).add(getCostOfGoods(overviewPage.getTax().getText())))
                .isEqualTo(getCostOfGoods(overviewPage.getTotal().getText()));
    }

    @Step("Проверка подтверждения отправки заказа")
    public void orderConfirmation() {
        assertThat(checkoutCompletePage.getText().exists()).as("Заказ не отправлен").isTrue();
    }

    @Step("Проверка отображения главной страницы")
    public void homepageIsOpen() {
        assertThat(homePage.getProductTableElements().isEmpty()).as("Карточки товаров не отображаются").isFalse();
    }

    @Step("Проверка удаления товара")
    public void productRemoval() {
        assertThat(badge.isDisplayed()).as("Бейдж количества товаров в корзине отображается").isFalse();
        assertThat(shoppingContainerPage.getCards().isEmpty()).as("Товар из корзины не удален").isTrue();
    }

    @Step("Проверка кнопки удаления")
    public void checkTheDeleteButton() {
        assertThat(homePage.getDeleteButton().exists()).isFalse();
    }

    @Step
    public void checkTheAuthorizationPage() {
        assertThat(authorizationPage.getLogginButton().isDisplayed()).isTrue();
    }

}
