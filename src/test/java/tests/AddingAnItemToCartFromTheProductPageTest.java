package tests;

import Data.User.UserData;
import PageObject.Elements.*;
import PageObject.Elements.MainPage.HomePage;
import Person.Person;
import Servise.ChromeDriver.BaseClass;
import Step.Steps;
import ToolBar.ToolBarElements;
import org.testng.annotations.Test;

public class AddingAnItemToCartFromTheProductPageTest extends BaseClass implements ToolBarElements {

    private CardsGoodsInTheCartPage cardsGoodsInTheCartElements = new CardsGoodsInTheCartPage();
    private CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();
    private CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
    private AuthorizationPage authorization = new AuthorizationPage();
    private OrderFormPage orderForm = new OrderFormPage();
    private Person person = Person.randomized();
    private HomePage homePage = new HomePage();
    private ProductCardPage productCardPage = new ProductCardPage();
    private Steps steps = new Steps();
    private String price;

    @Test(description = "Добавить товар в корзину из карточки и оформить заказ", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingAnItemToCart(UserData data) {
        /**Авторизация*/
        authorization.fillInFields(data.getUser(), data.getPassword());

        /**Очищение корзины*/
        homePage.removeFromCart();

        /**Открыть карточку товара*/
        homePage.getProductName().click();
        price = productCardPage.getPrice().getText();

        /**Добавить товар в корзину*/
        productCardPage.getAddButton().click();
        steps.checkOfAddingAnItemToTheCart();

        /**Открытие корзины*/
        steps.cartOpeningCheck(price);

        cardsGoodsInTheCartElements.getCheckout().click();
        steps.checkOpeningOfTheDataFillingForm();

        /** Заполнение данных для оформления заказа*/
        orderForm.getFirstName().val(person.getName());
        orderForm.getLastName().val(person.getLastName());
        orderForm.getPostalCode().val(person.getPostalCode());

        /**Проверка данных заказа*/
        orderForm.getContinueButton().click();
        steps.orderPlacement();

        overviewPage.getFinishButton().click();
        steps.orderConfirmation();

        checkoutCompletePage.getBackHomeButton().click();
        steps.homepageIsOpen();
    }
}
