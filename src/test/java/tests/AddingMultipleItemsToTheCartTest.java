package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.Elements.*;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

public class AddingMultipleItemsToTheCartTest extends BaseClass implements ToolBarElements, MenuPage {
    private static final Logger log = Logger.getLogger(AddingMultipleItemsToTheCartTest.class.getName());

    @Test(description = "Добавление нескольких товаров в корзину", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingMultipleItemsToTheCart(UserData data) {

        log.info(data.getUser());

        AuthorizationPage authorizationPage = openLoginPage();

        HomePage homePage = authorizationPage.login(data);
        homePage
                .removeFromCart()
                .doAddMultipleItemsToCart(3)
                .checkingTheAdditionOfGoods();

        List<ProductPojo> list = homePage.getProductsInCart();

        CardsGoodsInTheCartPage cartPage = openCart();
        List<ProductPojo> pojoList = cartPage.getAllProducts();
        cartPage.compareProducts(list);

        OrderFormPage orderFormPage = cartPage.openOrderPage();
        orderFormPage.dataFillingPerson();

        CheckoutOverviewPage overviewPage = orderFormPage.doClickButtonContinue();

        overviewPage
                .orderPlacement()
                .compareProducts(pojoList);

        CheckoutCompletePage completePage = overviewPage.doClickButtonFinish();

        completePage
                .orderConfirmation()
                .doClickButtonBackHome()
                .homepageIsOpen();


        if (!menuWindow.isDisplayed()) {
            openMenu();
        }
        logOut()
                .checkTheAuthorizationPage();
    }

}
