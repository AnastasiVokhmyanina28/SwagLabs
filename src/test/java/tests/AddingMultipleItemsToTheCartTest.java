package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.Elements.*;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Person.Person;
import Servise.ChromeDriver.BaseClass;
import Step.Steps;
import org.testng.annotations.Test;

import java.util.List;

public class AddingMultipleItemsToTheCartTest extends BaseClass implements ToolBarElements {
    private Person person = Person.randomized();

    @Test(description = "Добавление нескольких товаров в корзину", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingMultipleItemsToTheCart(UserData data) {
        /**Авторизация*/
        new AuthorizationPage().fillInFields(data.user, data.getPassword());

        HomePage homePage = new HomePage();

        /**Очистка*/
        homePage.removeFromCart();

        /**Добавить несколько товаров в корзину*/
        Steps step = new Steps();
        homePage.doAddMultipleItemsToCart();
        List<ProductPojo> list = homePage.getProductsInCart();
        step.checkingTheAdditionOfGoods();

        /** Перейти в корзину*/
        openContainer();
        CardsGoodsInTheCartPage cartPage = new CardsGoodsInTheCartPage();
        List<ProductPojo> pojoList = cartPage.getAllProducts();
        cartPage.compareProducts(list);

        /**Заполнить данные для заказа*/
        cartPage.doClickButtonCheckout();
        OrderFormPage orderFormPage = new OrderFormPage();
        orderFormPage.getFirstName().setValue(person.getName());
        orderFormPage.getLastName().setValue(person.getLastName());
        orderFormPage.getPostalCode().setValue(person.getPostalCode());
        orderFormPage.doClickButtonContinue();

        /**Проверка чека*/
        step.orderPlacement();
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        overviewPage.compareProducts(pojoList);

        /**Завершение оформления заказа*/
        overviewPage.doClickButtonFinish();
        step.orderConfirmation();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

        checkoutCompletePage.doClickButtonBackHome();
        step.homepageIsOpen();

        /**Выход с сайта*/
        MenuPage menuPage = new MenuPage();
        if (!menuPage.getMenu().isDisplayed()) {
            menu.click();
        }
        new MenuPage().getLogout().click();
        step.checkTheAuthorizationPage();
    }

}
