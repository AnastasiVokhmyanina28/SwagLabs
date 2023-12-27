package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.Elements.*;
import PageObject.Elements.MainPage.HomePage;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.List;

public class AddingAnItemToCartFromTheProductPageTest extends BaseClass {

    @Test(description = "Добавить товар в корзину из карточки и оформить заказ", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingAnItemToCart(UserData userData) {
        /**Авторизация*/

        AuthorizationPage auth = openLoginPage();

        HomePage homePage = auth.login(userData);

        homePage
                .removeFromCart();

        ProductCardPage productCardPage = homePage
                .getCardPage();

        productCardPage
                .addProduct()
                .checkOfAddingAnItemToTheCart();

        List<ProductPojo> allProductsFromMainPage = productCardPage.getAllProducts();

        CardsGoodsInTheCartPage cardsGoodsInTheCartElements = productCardPage.openCart();

        cardsGoodsInTheCartElements.compareProducts(allProductsFromMainPage);
        cardsGoodsInTheCartElements.cartOpeningCheck();

        OrderFormPage formPage = cardsGoodsInTheCartElements.openOrderPage();

        formPage
                .assertPageActive()
                .dataFillingPerson();

        CheckoutOverviewPage overviewPage = formPage.doClickButtonContinue();
        overviewPage.orderPlacement();

        CheckoutCompletePage completePage = overviewPage.doClickButtonFinish();
        completePage.orderConfirmation();

        completePage.doClickButtonBackHome()
                .homepageIsOpen();
    }
}
