package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.Elements.AuthorizationPage;
import PageObject.Elements.CardsGoodsInTheCartPage;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.List;

public class AddingAnItemToCartFromTheHomePageTest extends BaseClass implements ToolBarElements {

    @Test(description = "Добавление товара в корзину с главной страницы", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingAnItemToCart(UserData userData) {

        AuthorizationPage authorizationPage = openLoginPage();

        HomePage homePage = authorizationPage.login(userData);

        homePage.removeFromCart()
                .addItemToCart();

        List<ProductPojo> pojoList = homePage.getProductsInCart();

        CardsGoodsInTheCartPage cartPage = openContainer();

        cartPage.cartOpeningCheck()
                .compareProducts(pojoList);
    }
}
