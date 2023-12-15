package tests;

import Data.UserData;
import PageObject.Elements.AuthorizationElements;
import PageObject.Elements.CardsGoodsInTheCartElements;
import PageObject.Page.*;
import Person.Person;
import Servise.BaseStep;
import Servise.ChromeDriver.BaseClass;
import Step.Steps;
import ToolBar.ToolBarElements;
import org.testng.annotations.Test;

public class AddingAnItemToCartFromTheProductPageTest extends BaseClass implements ToolBarElements {

    private CardsGoodsInTheCartElements cardsGoodsInTheCartElements = new CardsGoodsInTheCartElements();
    private CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();
    private CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
    private AuthorizationPage authorization = new AuthorizationPage();
    private OrderFormPage orderForm = new OrderFormPage();
    private Person person = Person.randomized();
    private BaseStep baseStep = new BaseStep();
    private HomePage homePage = new HomePage();
    private CardPage cardPage = new CardPage();
    private Steps steps = new Steps();
    private String price;

    @Test(description = "Добавить товар в корзину из карточки и оформить заказ", dataProvider = "authParamUser", dataProviderClass = AuthorizationElements.class)
    public void addingAnItemToCart(UserData data) {
        /**Авторизация*/
        authorization.elements.fillInFields(data.getUser(), data.getPassword());

        /**Очищение корзины*/
        baseStep.emptyYourCart();

        /**Открыть карточку товара*/
        homePage.homeElements.getProductName().click();
        price = cardPage.productCardElements.getPrice().getText();

        /**Добавить товар в корзину*/
        cardPage.productCardElements.getAddButton().click();
        steps.checkOfAddingAnItemToTheCart();

        /**Открытие корзины*/
        steps.cartOpeningCheck(price);

        cardsGoodsInTheCartElements.getCheckout().click();
        steps.checkOpeningOfTheDataFillingForm();

        /** Заполнение данных для оформления заказа*/
        orderForm.orderFormElements.getFirstName().val(person.getName());
        orderForm.orderFormElements.getLastName().val(person.getLastName());
        orderForm.orderFormElements.getPostalCode().val(person.getPostalCode());

        /**Проверка данных заказа*/
        orderForm.orderFormElements.getContinueButton().click();
        steps.orderPlacement();

        overviewPage.overviewElements.getFinishButton().click();
        steps.orderConfirmation();

        checkoutCompletePage.completeElements.getBackHomeButton().click();
        steps.homepageIsOpen();
    }
}
