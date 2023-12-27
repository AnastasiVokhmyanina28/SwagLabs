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
        AuthorizationPage authorizationPage = openLoginPage();

        HomePage homePage = authorizationPage.login(data);
        homePage.removeFromCart();

        ProductCardPage productCardPage = homePage
                .getCardPage();

        productCardPage
                .addProduct()
                .checkOfAddingAnItemToTheCart();

        List<ProductPojo> allProductsFromMainPage = productCardPage.getAllProducts();

        CardsGoodsInTheCartPage cardsGoodsInTheCartElements = openCart();

        cardsGoodsInTheCartElements
                .cartOpeningCheck()
                .compareProducts(allProductsFromMainPage);

        OrderFormPage formPage = cardsGoodsInTheCartElements.openOrderPage();

        formPage
                .assertPageActive()
                .dataFillingPerson();

        CheckoutOverviewPage overviewPage = formPage.doClickButtonContinue();

        overviewPage
                .orderPlacement();

        CheckoutCompletePage completePage = overviewPage.doClickButtonFinish();

        completePage
                .orderConfirmation()
                .doClickButtonBackHome()
                .homepageIsOpen();
    }
}
