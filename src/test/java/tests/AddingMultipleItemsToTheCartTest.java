package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.Elements.*;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.List;

public class AddingMultipleItemsToTheCartTest extends BaseClass implements ToolBarElements, MenuPage {

    @Test(description = "Добавление нескольких товаров в корзину", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingMultipleItemsToTheCart(UserData data) {

        AuthorizationPage authorizationPage = openLoginPage();

        HomePage homePage = authorizationPage.login(data);
        homePage
                .removeFromCart()
                .doAddMultipleItemsToCart()
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
