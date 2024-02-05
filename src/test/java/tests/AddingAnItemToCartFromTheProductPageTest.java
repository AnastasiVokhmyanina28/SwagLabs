package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.*;
import PageObject.MainPage.HomePage;
import PageObject.blocks.ToolBar.ToolBarElements;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

public class AddingAnItemToCartFromTheProductPageTest extends BaseClass implements ToolBarElements {
    private static final Logger log = Logger.getLogger(AddingAnItemToCartFromTheProductPageTest.class.getName());


    @Test(description = "Добавить товар в корзину из карточки и оформить заказ", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingAnItemToCart(UserData data) {

        log.info(data.getUser());

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
