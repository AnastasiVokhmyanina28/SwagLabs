package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.Elements.*;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.List;

public class AddingAnItemToCartFromTheProductPageTest extends BaseClass implements ToolBarElements {

    @Test(description = "Добавить товар в корзину из карточки и оформить заказ", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingAnItemToCart(UserData data) {
        /**Авторизация*/
        new AuthorizationPage().fillInFields(data.getUser(), data.getPassword());

        HomePage homePage = new HomePage();
        /**Очищение корзины*/
        homePage.removeFromCart();

        /**Открыть карточку товара*/
        homePage.getCardPage();
        ProductCardPage productCardPage = new ProductCardPage();

        /**Добавить товар в корзину*/
        productCardPage.getAddButton().click();
        List<ProductPojo> list = productCardPage.getAllProducts();

        productCardPage.checkOfAddingAnItemToTheCart();

        CardsGoodsInTheCartPage cardsGoodsInTheCartElements = new CardsGoodsInTheCartPage();
        /**Открытие корзины*/
        openContainer();
        cardsGoodsInTheCartElements.compareProducts(list);
        cardsGoodsInTheCartElements.cartOpeningCheck();
        cardsGoodsInTheCartElements.doClickButtonCheckout();

        OrderFormPage formPage = new OrderFormPage();
        formPage.assertPageActive();

        /** Заполнение данных для оформления заказа*/
        formPage.dataFillingPerson();

        /**Проверка данных заказа*/
        formPage.doClickButtonContinue();
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        overviewPage.orderPlacement();

        overviewPage.doClickButtonFinish();
        CheckoutCompletePage completePage = new CheckoutCompletePage();
        completePage.orderConfirmation();

        completePage.doClickButtonBackHome();
        homePage.homepageIsOpen();
    }
}
