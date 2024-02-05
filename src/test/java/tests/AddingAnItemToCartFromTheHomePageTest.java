package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.AuthorizationPage;
import PageObject.CardsGoodsInTheCartPage;
import PageObject.MainPage.HomePage;
import PageObject.blocks.ToolBar.ToolBarElements;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

public class AddingAnItemToCartFromTheHomePageTest extends BaseClass implements ToolBarElements {
    private static final Logger log = Logger.getLogger(AddingAnItemToCartFromTheHomePageTest.class.getName());


    @Test(description = "Добавление товара в корзину с главной страницы", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingAnItemToCart(UserData userData) {

        log.info(userData.getUser());

        AuthorizationPage authorizationPage = openLoginPage();

        HomePage homePage = authorizationPage.login(userData);

        homePage.removeFromCart()
                .doAddMultipleItemsToCart(1);

        List<ProductPojo> pojoList = homePage.getProductsInCart();

        CardsGoodsInTheCartPage cartPage = openCart();

        cartPage.cartOpeningCheck()
                .compareProducts(pojoList);
    }
}
