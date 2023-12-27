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
        HomePage homePage = new AuthorizationPage().fillInFields(data.getUser(), data.getPassword());

        homePage.removeFromCart()
                .doAddMultipleItemsToCart()
                .checkingTheAdditionOfGoods();

        List<ProductPojo> list = homePage.getProductsInCart();

        CardsGoodsInTheCartPage cartPage = openContainer();
        List<ProductPojo> pojoList = cartPage.getAllProducts();
        cartPage.compareProducts(list);

        OrderFormPage orderFormPage = cartPage.doClickButtonCheckout();
        orderFormPage.dataFillingPerson();

        CheckoutOverviewPage overviewPage = orderFormPage.doClickButtonContinue();
        overviewPage.orderPlacement();
        overviewPage.compareProducts(pojoList);

        CheckoutCompletePage completePage = overviewPage.doClickButtonFinish();

        completePage.orderConfirmation();
        completePage.doClickButtonBackHome()
                .homepageIsOpen();
//???
        MenuPage menuPage = new MenuPage();
        if (!menuPage.getMenu().isDisplayed()) {
            menuPage.openMenu();
        }
        menuPage.logOut()
                .checkTheAuthorizationPage();
    }

}
