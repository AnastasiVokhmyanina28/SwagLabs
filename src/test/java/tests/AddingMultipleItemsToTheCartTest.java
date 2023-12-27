package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.Elements.*;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.List;

public class AddingMultipleItemsToTheCartTest extends BaseClass implements ToolBarElements {

    @Test(description = "Добавление нескольких товаров в корзину", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingMultipleItemsToTheCart(UserData data) {
        /**Авторизация*/
        AuthorizationPage authorizationPage = new AuthorizationPage();
        authorizationPage.login(data.user, data.getPassword());

        HomePage homePage = new HomePage();

        /**Очистка*/
        homePage.removeFromCart();

        /**Добавить несколько товаров в корзину*/
        homePage.doAddMultipleItemsToCart();
        List<ProductPojo> list = homePage.getProductsInCart();
        homePage.checkingTheAdditionOfGoods();

        /** Перейти в корзину*/
        openCart();
        CardsGoodsInTheCartPage cartPage = new CardsGoodsInTheCartPage();
        List<ProductPojo> pojoList = cartPage.getAllProducts();
        cartPage.compareProducts(list);

        /**Заполнить данные для заказа*/
        cartPage.openOrderPage();
        OrderFormPage orderFormPage = new OrderFormPage();
        orderFormPage.dataFillingPerson();
        orderFormPage.doClickButtonContinue();

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        /**Проверка чека*/
        overviewPage.orderPlacement();
        overviewPage.compareProducts(pojoList);

        /**Завершение оформления заказа*/
        overviewPage.doClickButtonFinish();
        CheckoutCompletePage completePage = new CheckoutCompletePage();
        completePage.orderConfirmation();
        completePage.doClickButtonBackHome();
        homePage.homepageIsOpen();

        /**Выход с сайта*/
        MenuPage menuPage = new MenuPage();
        if (!menuPage.getMenu().isDisplayed()) {
            menu.click();
        }
        menuPage.getLogout().click();
        authorizationPage.checkTheAuthorizationPage();
    }

}
