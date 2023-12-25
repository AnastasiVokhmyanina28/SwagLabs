package tests;

import Data.User.UserData;
import PageObject.Elements.*;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Person.Person;
import Servise.ChromeDriver.BaseClass;
import Step.Steps;
import org.testng.annotations.Test;

public class AddingAnItemToCartFromTheProductPageTest extends BaseClass implements ToolBarElements {
    private Person person = Person.randomized();
    private String price;

    @Test(description = "Добавить товар в корзину из карточки и оформить заказ", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingAnItemToCart(UserData data) {
        /**Авторизация*/
        new AuthorizationPage().fillInFields(data.getUser(), data.getPassword());

        HomePage homePage = new HomePage();
        /**Очищение корзины*/
        homePage.removeFromCart();

        /**Открыть карточку товара*/
        homePage.getProductName().click();
        ProductCardPage productCardPage = new ProductCardPage();
        price = productCardPage.getPrice().getText();

        /**Добавить товар в корзину*/
        productCardPage.getAddButton().click();
        Steps steps = new Steps();
        steps.checkOfAddingAnItemToTheCart();

        /**Открытие корзины*/
        steps.cartOpeningCheck(price);

        CardsGoodsInTheCartPage cardsGoodsInTheCartElements = new CardsGoodsInTheCartPage();

        cardsGoodsInTheCartElements.doClickButtonCheckout();
        steps.checkOpeningOfTheDataFillingForm();

        OrderFormPage orderForm = new OrderFormPage();

        /** Заполнение данных для оформления заказа*/
        orderForm.getFirstName().setValue(person.getName());
        orderForm.getLastName().setValue(person.getLastName());
        orderForm.getPostalCode().setValue(person.getPostalCode());

        /**Проверка данных заказа*/
        orderForm.doClickButtonContinue();
        steps.orderPlacement();

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();

        overviewPage.doClickButtonFinish();
        steps.orderConfirmation();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

        checkoutCompletePage.doClickButtonBackHome();
        steps.homepageIsOpen();
    }
}
